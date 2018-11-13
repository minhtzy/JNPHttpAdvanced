/**
 *
 * Test
 * Created by @minht on Oct 25, 2018
 */
package jnp.tmg.client;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import jnp.tmg.modules.http.Header;
import jnp.tmg.modules.http.Headers;
import jnp.tmg.utils.JsonUtils;

public class Test {

    public static void main(String[] args) {
        try {
            HttpRequester client = new HttpRequester(new URL("http://airtravelling.net/hot-deals"));
            
            Headers requestHeader = new Headers();
            requestHeader.getEntities().add(new Header("Content-Type","application/x-www-form-urlencoded"));
            requestHeader.getEntities().add(new Header("charset","utf-8"));
            client.setRequestHeaders(requestHeader);
            String urlParameters = "from=HAN&to=SGN";
            client.setBody(urlParameters);
            
            client.sendPost();
            
            System.out.println(JsonUtils.parseJson(client.getResponseBody()).get("price"));
            
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
