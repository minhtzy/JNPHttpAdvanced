/**
 *
 * SendHttpRequest
 * Created by @minht on Oct 15, 2018
 */
package jnp.tmg.client;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import jnp.tmg.modules.assertion.AssertParameter;
import jnp.tmg.modules.http.Body;
import jnp.tmg.modules.http.Headers;
import jnp.tmg.user.ClientConfig;
import jnp.tmg.utils.IOUtils;

public class SendHttpRequest {

    public static void main(String[] args) throws URISyntaxException, IOException {
        sendGet(new URI("https://www.wikipedia.org"));
    }

    public static void sendGet(URI uri) throws MalformedURLException, IOException {
        AssertParameter.notNull(uri, "uri");
        URL url = uri.toURL();

        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod(("GET"));
        con.setRequestProperty("User-Agent", ClientConfig.USER_AGENT);

        int responeCode = con.getResponseCode();
        System.out.println("Status: " + responeCode + " " + con.getResponseMessage());
        if (HttpURLConnection.HTTP_OK == responeCode) {
            
            System.out.println("Headers : ");
            Map<String, List<String>> headers = con.getHeaderFields();
            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                String key = entry.getKey();
                List<String> value = entry.getValue();
                System.out.println();
                System.out.println(key + " = " + value.toString());
            }
            System.out.println("Body");
            byte[] buffers = IOUtils.toByteArray(con.getInputStream());
            String responeBody = new String(buffers);
            System.out.println(responeBody);

        } else {

        }
    }
}
