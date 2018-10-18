/**
 *
 * SendHttpRequest
 * Created by @minht on Oct 15, 2018
 */
package jnp.tmg.client;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import jnp.tmg.modules.assertion.AssertParameter;
import jnp.tmg.modules.http.Body;
import jnp.tmg.modules.http.Header;
import jnp.tmg.modules.http.Headers;
import jnp.tmg.user.ClientConfig;
import jnp.tmg.utils.IOUtils;

public class HttpClient {

    private URL url;
    private Headers requestHeaders;
    private Headers responeHeaders;
    private Body requestBody;
    private byte[] responeBodyBytes;
    
    public HttpClient() {
    }

    public HttpClient(URL url) {
        this.url = url;
    }

    public HttpClient(URL url, Headers requestHeaders) {
        this.url = url;
        this.requestHeaders = requestHeaders;
    }

    public HttpClient(URL url, Headers requestHeaders,Body requestBody ) {
        this.url = url;
        this.requestHeaders = requestHeaders;
        this.requestBody = requestBody;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public Headers getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(Headers requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public Headers getResponeHeaders() {
        return responeHeaders;
    }

    public Body getBody() {
        return requestBody;
    }

    public void setBody(Body requestBody) {
        this.requestBody = requestBody;
    }
    
    public void sendGet() throws MalformedURLException, IOException {
        AssertParameter.notNull(url, "uri");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod(("GET"));
        con.setRequestProperty("User-Agent", ClientConfig.USER_AGENT);
        for(Header header : requestHeaders) {
             con.addRequestProperty(header.getKey(),header.getValue());
        }
        getContent(con);
    }
    
    private void getContent(HttpURLConnection con) throws IOException {
        System.out.println("Get Content");
        if(con != null) {
            int responeCode = con.getResponseCode();
             System.out.println("Status: " + responeCode + " " + con.getResponseMessage());
            if(responeCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Headers : ");
                Map<String, List<String>> headers = con.getHeaderFields();
                
                List<Header> listHeaders = new LinkedList<Header>();
                for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                    String key = entry.getKey();
                    List<String> value = entry.getValue();
                    Header header = new Header(key, value.toString());
                    listHeaders.add(header);
                    
                    System.out.println();
                    System.out.println(key + " = " + value.toString());
                }
                responeHeaders = new Headers(listHeaders);
                System.out.println("Body");
                responeBodyBytes = IOUtils.toByteArray(con.getInputStream());
                String responeBody = new String(responeBodyBytes);
                System.out.println(responeBody);
            }
        }
    }
}
