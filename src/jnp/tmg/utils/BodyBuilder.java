/**
*
* BodyBuilder 
* Created by @minht on Oct 27, 2018
*/

package jnp.tmg.utils;

import jnp.tmg.modules.assertion.AssertParameter;
import jnp.tmg.modules.http.ContentType;
import jnp.tmg.modules.http.Headers;


public class BodyBuilder {
    private ContentType contentType;
    private Headers keysAndValues;
    private String textContent;
    public BodyBuilder() {
        
    }
    public BodyBuilder setContentType(ContentType contentType) {
        try {
            AssertParameter.notNull(contentType, "Content-Type");
            this.contentType = contentType;
        } catch (Exception e) {
            this.contentType = ContentType.ANY;
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
    public String build() {
        String strBody = "";
        //if(contentType.equals(this) ) ;
        return strBody;
    }
}
