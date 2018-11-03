/**
 *
 * Header
 * Created by @minht on Oct 17, 2018
 */
package jnp.tmg.modules.http;

import java.util.List;
import jnp.tmg.common.KeyAndValue;
import jnp.tmg.modules.assertion.AssertParameter;

public class Header implements KeyAndValue {

    private String key;
    private String value;
    private String description;

    public Header() {
    }

    public Header(String key, String value) {
        this.key = key;
        this.value = value;
    }
    
    public Header(String key,List<String> values) {
        this.key = key;
        this.value = String.join(", ", values);
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
        final StringBuilder builder = new StringBuilder();
        if (key != null && value != null) {
            builder.append(key).append(" \u2192 ").append(value);
        }
        return builder.toString();
    }

    public boolean hasSameKeyAs(Header header) {
        AssertParameter.notNull(header, Header.class);
        return this.key.equalsIgnoreCase(header.getKey());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Header header = (Header) o;

        // HTTP header names are always case-insensitive. Values are usually case-insensitive.
        if (key != null ? !key.equalsIgnoreCase(header.key) : header.key != null) {
            return false;
        }
        if (value != null ? !value.equalsIgnoreCase(header.value) : header.value != null) {
            return false;
        }

        return true;
    }
}
