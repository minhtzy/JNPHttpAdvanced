/**
*
* ContentType 
* Created by @minht on Oct 14, 2018
*/

package jnp.tmg.modules.http;

import static java.lang.String.format;
import java.nio.charset.Charset;
import static java.util.Arrays.asList;
import java.util.Iterator;
import jnp.tmg.utils.StringUtils;


public enum ContentType {

    
    ANY("*/*"),
    
    TEXT("text/plain"),
    
    JSON("application/json", "application/javascript", "text/javascript", "text/json"),
    
    XML("application/xml", "text/xml", "application/xhtml+xml"),
    
    HTML("text/html"),
    
    URLENC("application/x-www-form-urlencoded"),
    
    BINARY("application/octet-stream");

    private static final String PLUS_XML = "+xml";
    private static final String PLUS_JSON = "+json";
    private static final String PLUS_HTML = "+html";

    private static String getContentTypeWithoutCharset(String toLowerCase) {
        return toLowerCase.split(";")[0].trim();
    }

    private final String[] ctStrings;

    public String[] getContentTypeStrings() {
        return ctStrings;
    }

    @Override
    public String toString() {
        return ctStrings[0];
    }

    @SuppressWarnings("unchecked")
    public String getAcceptHeader() {
        Iterator<String> iter = asList(ctStrings).iterator();
        StringBuilder sb = new StringBuilder();
        while (iter.hasNext()) {
            sb.append(iter.next());
            if (iter.hasNext()) sb.append(", ");
        }
        return sb.toString();
    }

    public String withCharset(Charset charset) {
        if (charset == null) {
            throw new IllegalArgumentException("charset cannot be null");
        }
        return withCharset(charset.toString());
    }

    public String withCharset(String charset) {
        if (charset.isEmpty()) {
            throw new IllegalArgumentException("charset cannot be empty");
        }
        return format("%s; charset=%s", this.toString(), charset.trim());
    }

    private ContentType(String... contentTypes) {
        this.ctStrings = contentTypes;
    }

    public static ContentType fromContentType(String contentType) {
        if (contentType == null) {
            return null;
        }
        contentType = getContentTypeWithoutCharset(contentType.toLowerCase());
        final ContentType foundContentType;
        if ( StringUtils.contains(XML.ctStrings,contentType) || StringUtils.endsWithIgnoreCase(contentType, PLUS_XML)) {
            foundContentType = XML;
        } else if (StringUtils.contains(JSON.ctStrings, contentType) || StringUtils.endsWithIgnoreCase(contentType, PLUS_JSON)) {
            foundContentType = JSON;
        } else if (StringUtils.contains(TEXT.ctStrings, contentType)) {
            foundContentType = TEXT;
        } else if (StringUtils.contains(HTML.ctStrings, contentType) || StringUtils.endsWithIgnoreCase(contentType, PLUS_HTML)) {
            foundContentType = HTML;
        } else if (StringUtils.contains(URLENC.ctStrings, contentType)) {
            foundContentType = URLENC;
        } else if (StringUtils.contains(BINARY.ctStrings, contentType)) {
            foundContentType = BINARY;
        } else if (StringUtils.contains(ANY.ctStrings, contentType)) {
            foundContentType = ANY;
        } else {
            foundContentType = null;
        }
        return foundContentType;
    }

    public boolean matches(String contentType) {
        if(contentType == null) {
            return false;
        }
        String expectedContentType = contentType.trim();
        if (expectedContentType.isEmpty()) {
            return false;
        }
        for (String supportedContentType : getContentTypeStrings()) {
            if (supportedContentType.equalsIgnoreCase(expectedContentType)) {
                return true;
            }
        }
        return false;
    }
}