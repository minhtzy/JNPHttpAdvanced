/**
*
* BodyBuilder 
* Created by @minht on Oct 27, 2018
*/

package jnp.tmg.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import jnp.tmg.modules.assertion.AssertParameter;
import jnp.tmg.modules.http.ContentType;
import jnp.tmg.modules.http.Header;
import jnp.tmg.modules.http.Headers;


public class BodyBuilder {
    private String contentType;
    //private String charSet;
    private Headers keysAndValues;
    private String textContent;
    public BodyBuilder() {
        
    }
    public BodyBuilder setContentType(String contentType) {
        try {
            AssertParameter.notNull(contentType, "Content-Type");
            this.contentType = contentType;
        } catch (Exception e) {
            this.contentType = ContentType.ANY.toString();
        }
        return this;
    } 
    public BodyBuilder setKeysAndValues(Headers keysAndValues) {
        this.keysAndValues = keysAndValues;
        return this;
    }
    public BodyBuilder setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }
    public String build() throws UnsupportedEncodingException {
        ContentType foundContentType = ContentType.fromContentType(contentType);
        if( foundContentType == ContentType.ANY || foundContentType == ContentType.URLENC ) {
            StringBuilder bodyBuilder = new StringBuilder();
            //if(contentType.equals(this) ) ;
            for(Header h : keysAndValues) {
                bodyBuilder.append(h.getKey());
                bodyBuilder.append("=");
                bodyBuilder.append(h.getValue());
                bodyBuilder.append("&");
            }
            return URLEncoder.encode(bodyBuilder.toString(),"UTF-8");
        }
        else {
            return "";    
        }   
    }
}
