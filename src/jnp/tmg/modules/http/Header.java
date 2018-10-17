/**
*
* Header 
* Created by @minht on Oct 17, 2018
*/

package jnp.tmg.modules.http;

import jnp.tmg.common.KeyAndValue;


public class Header implements KeyAndValue{
private String key;
private String value;
private String description;

    public Header() {
    }

    public Header(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public Header(String key, String value, String description) {
        this.key = key;
        this.value = value;
        this.description = description;
    }
    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
        
    @Override
    public String toString() {
        return key + "=" + value;
    }
    
}
