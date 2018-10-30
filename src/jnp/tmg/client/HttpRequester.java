/**
 *
 * SendHttpRequest
 * Created by @minht on Oct 15, 2018
 */
package jnp.tmg.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import jnp.tmg.modules.assertion.AssertParameter;
import jnp.tmg.modules.http.Header;
import jnp.tmg.modules.http.Headers;
import jnp.tmg.config.ClientConfig;
import jnp.tmg.utils.IOUtils;

public class HttpRequester {

    private URI uri;
    int statusCode;
    String statusMessage;
    private Headers requestHeaders;
    private Headers responeHeaders;
    private String requestBody;
    private String responeBody;
    private byte[] responeBodyBytes;

    public HttpRequester() {
    }

    public HttpRequester(URI uri) {
        this.uri = uri;
        this.requestHeaders = new Headers(new ArrayList<>());
    }

    public HttpRequester(URI uri, Headers requestHeaders) {
        this.uri = uri;
        this.requestHeaders = requestHeaders;
    }

    public HttpRequester(URI uri, Headers requestHeaders, String requestBody) {
        this.uri = uri;
        this.requestHeaders = requestHeaders;
        this.requestBody = requestBody;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

 
    public URI getUrl() {
        return uri;
    }

    public void setUrl(URI uri) {
        this.uri = uri;
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

    public String getResponseBody() {
        return responeBody;
    }

    public void setBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public void sendGet() throws MalformedURLException, IOException {
        AssertParameter.notNull(uri, "uri");

        URL url = uri.toURL();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod(("GET"));
        con.setRequestProperty("User-Agent", ClientConfig.USER_AGENT);
        for (Header header : requestHeaders) {
            con.addRequestProperty(header.getKey(), header.getValue());
        }
        getContent(con);
    }

    public void sendPost() throws IOException {
        AssertParameter.notNull(uri, "uri");

        URL url = uri.toURL();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("User-Agent", ClientConfig.USER_AGENT);
        for (Header header : requestHeaders) {
            con.addRequestProperty(header.getKey(), header.getValue());
        }
        try (DataOutputStream out = new DataOutputStream(con.getOutputStream())) {
            out.write(IOUtils.toByteArray((String) requestBody, Charset.forName("UTF-8")));
            out.flush();
        }
        getContent(con);
    }
    
   
    public void sendHead() throws MalformedURLException, IOException {
        AssertParameter.notNull(uri, "uri");
        
        URL url = uri.toURL();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod(("HEAD"));
        con.setRequestProperty("User-Agent", ClientConfig.USER_AGENT);
        for (Header header : requestHeaders) {
            con.addRequestProperty(header.getKey(), header.getValue());
        }
        getContent(con);
    }

    
    public void sendDelete() throws MalformedURLException, IOException {
        AssertParameter.notNull(uri, "uri");

        URL url = uri.toURL();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("DELETE");
        con.setDoOutput(true);
        con.setRequestProperty("User-Agent", ClientConfig.USER_AGENT);
        for (Header header : requestHeaders) {
            con.addRequestProperty(header.getKey(), header.getValue());
        }
        try (DataOutputStream out = new DataOutputStream(con.getOutputStream())) {
            out.write(IOUtils.toByteArray((String) requestBody, Charset.forName("UTF-8")));
            out.flush();
        }
        
        getContent(con);
    }
    
    
    public void sendPut() throws MalformedURLException, ProtocolException, IOException {
        AssertParameter.notNull(uri, "uri");

        URL url = uri.toURL();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("PUT");
        con.setDoOutput(true);
        con.setRequestProperty("User-Agent", ClientConfig.USER_AGENT);
        for (Header header : requestHeaders) {
            con.addRequestProperty(header.getKey(), header.getValue());
        }
        try (DataOutputStream out = new DataOutputStream(con.getOutputStream())) {
            out.write(IOUtils.toByteArray((String) requestBody, Charset.forName("UTF-8")));
            out.flush();
        }
        getContent(con);
    }

    private void getContent(HttpURLConnection con) throws IOException {
        System.out.println("Get Content");
        if (con != null) {
            statusCode = con.getResponseCode();
            statusMessage = con.getResponseMessage();
            int responeCode = con.getResponseCode();
            System.out.println("Status: " + responeCode + " " + con.getResponseMessage());

            System.out.println("Headers : ");
            Map<String, List<String>> headers = con.getHeaderFields();

            List<Header> listHeaders = new LinkedList<>();
            headers.entrySet().forEach((entry) -> {
                String key = entry.getKey();
                List<String> value = entry.getValue();
                Header header = new Header(key, value);
                listHeaders.add(header);
                System.out.println();
                System.out.println(header.getKey() + " = " + header.getValue());
            });

            try (InputStream input = con.getInputStream()) {
                if (input != null) {
                    responeHeaders = new Headers(listHeaders);
                    System.out.println("Body");
                    responeBodyBytes = IOUtils.toByteArray(input);
                    responeBody = new String(responeBodyBytes);
                    System.out.println(responeBody);
                }

            }
        }
    }
}
