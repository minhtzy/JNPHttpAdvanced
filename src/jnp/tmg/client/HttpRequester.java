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
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import jnp.tmg.modules.assertion.AssertParameter;
import jnp.tmg.modules.http.Header;
import jnp.tmg.modules.http.Headers;
import jnp.tmg.config.ClientConfig;
import jnp.tmg.modules.http.ContentType;
import jnp.tmg.modules.http.Cookie;
import jnp.tmg.modules.http.Cookies;
import jnp.tmg.utils.IOUtils;

public class HttpRequester {

    private URL url;
    int statusCode;
    String statusMessage;
    private Headers requestHeaders;
    private Headers responeHeaders;
    private String requestBody;
    private String responeBody;
    private byte[] responeBodyBytes;

    public HttpRequester() {
        requestHeaders = new Headers();
        responeHeaders = new Headers();
        requestBody = "";
        responeBody = "";
    }

    public HttpRequester(URL url) {
        this.url = url;
        this.requestHeaders = new Headers(new ArrayList<>());
    }

    public HttpRequester(URL url, Headers requestHeaders) {
        this.url = url;
        this.requestHeaders = requestHeaders;
    }

    public HttpRequester(URL url, Headers requestHeaders, String requestBody) {
        this.url = url;
        this.requestHeaders = requestHeaders;
        this.requestBody = requestBody;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
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

    public String getResponseBody() {
        return responeBody;
    }

    public void setBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public void sendGet() throws MalformedURLException, IOException {
        AssertParameter.notNull(url, "url");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod(("GET"));
        con.setRequestProperty("User-Agent", ClientConfig.USER_AGENT);
        for (Header header : requestHeaders) {
            con.addRequestProperty(header.getKey(), header.getValue());
        }
        getContent(con);
    }

    public void sendPost() throws MalformedURLException, IOException {
        AssertParameter.notNull(url, "url");
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
        AssertParameter.notNull(url, "url");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod(("HEAD"));
        con.setRequestProperty("User-Agent", ClientConfig.USER_AGENT);
        for (Header header : requestHeaders) {
            con.addRequestProperty(header.getKey(), header.getValue());
        }
        getContent(con);
    }

    public void sendDelete() throws MalformedURLException, IOException {
        AssertParameter.notNull(url, "url");
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
        AssertParameter.notNull(url, "url");
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

    public Cookies getCookies() {
        List<String> strCookies = responeHeaders.getValues("Set-Cookie");

        List<Cookie> listCookie = new LinkedList<>();
        for (String sc : strCookies) {
            Cookie cookie = Cookie.parse(sc);
            listCookie.add(cookie);
        }

        return new Cookies(listCookie);
    }

    private void getContent(HttpURLConnection con) {
        System.out.println("Get Content....");
        if (con != null) {
            try {
                statusCode = con.getResponseCode();
                statusMessage = con.getResponseMessage();
                int responeCode = con.getResponseCode();
                System.out.println("Status: " + responeCode + " " + con.getResponseMessage());

                System.out.println("Get Headers...");
                Map<String, List<String>> headers = con.getHeaderFields();

                List<Header> listHeaders = new LinkedList<>();
                System.out.println(headers);
                headers.entrySet().forEach((entry) -> {

                    String key = entry.getKey();
                    List<String> value = entry.getValue();
                    if (key != null && value != null) {
                        for (String vString : value) {
                            Header header = new Header(key, vString);
                            listHeaders.add(header);
                        }

                    }
//                System.out.println(key + "=" + value);
//                System.out.println(header);
                });

                System.out.println("Get Header Completed");
                responeHeaders = new Headers(listHeaders);
                try (InputStream input = con.getInputStream()) {
                    if (input != null) {
                        System.out.println("Get Body...");
                        responeBodyBytes = IOUtils.toByteArray(input);

                        Charset charset;
                        if (this.getResponeHeaders().hasEntity("Content-Type")) {
                            charset = ContentType.getCharset(getResponeHeaders().getValue("Content-Type").trim());
                        } else {
                            charset = Charset.forName("UTF-8");
                        }
                        responeBody = new String(responeBodyBytes, charset);
                        System.out.println("Get Body Completed");
                    }
                } catch (IOException ex) {
                }
            } catch (IOException ex) {
                Logger.getLogger(HttpRequester.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
