/**
 *
 * Cookie
 * Created by @minht on Oct 14, 2018
 */
package jnp.tmg.modules.http;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import jnp.tmg.common.KeyAndValue;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author minht
 */
public class Cookie implements KeyAndValue {
//  RFC 2109 - http://www.ietf.org/rfc/rfc2109.txt
    
    public static final String COMMENT = "Comment";
    public static final String PATH = "Path";
    public static final String DOMAIN = "Domain";
    public static final String MAX_AGE = "Max-Age";
    public static final String SECURE = "Secure";
    public static final String HTTP_ONLY = "HttpOnly";
    public static final String EXPIRES = "Expires";
    public static final String VERSION = "Version";

    private static final String COOKIE_ATTRIBUTE_SEPARATOR = ";";
    private static final String EQUALS = "=";
    private static final int UNDEFINED = -1;

    private String key;
    private String value;
    private String comment;
    private Date expiryDate;
    private String domain;
    private String path;
    private boolean secured;
    private boolean httpOnly;
    private int version;
    private int maxAge;

    public Cookie() {
    }

    public Cookie(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public Cookie(String key, String value, String comment, Date expiryDate, String domain, String path, boolean secured, boolean httpOnly, int version, int maxAge) {
        this.key = key;
        this.value = value;
        this.comment = comment;
        this.expiryDate = expiryDate;
        this.domain = domain;
        this.path = path;
        this.secured = secured;
        this.httpOnly = httpOnly;
        this.version = version;
        this.maxAge = maxAge;
    }

    public boolean hasValue() {
        return value != null;
    }

    public String getComment() {
        return comment;
    }

    public boolean hasComment() {
        return comment != null;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public boolean hasExpiryDate() {
        return expiryDate != null;
    }

    public String getDomain() {
        return domain;
    }

    public boolean hasDomain() {
        return domain != null;
    }

    public String getPath() {
        return path;
    }

    public boolean hasPath() {
        return path != null;
    }

    public boolean isSecured() {
        return secured;
    }

    public boolean isHttpOnly() {
        return httpOnly;
    }

    public int getVersion() {
        return version;
    }

    public boolean hasVersion() {
        return version != UNDEFINED;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public boolean hasMaxAge() {
        return maxAge != UNDEFINED;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(key);
        if (hasValue()) {
            builder.append(EQUALS).append(value);
        }
        if (hasComment()) {
            builder.append(COOKIE_ATTRIBUTE_SEPARATOR).append(COMMENT).append(EQUALS).append(comment);
        }
        if (hasPath()) {
            builder.append(COOKIE_ATTRIBUTE_SEPARATOR).append(PATH).append(EQUALS).append(path);
        }
        if (hasDomain()) {
            builder.append(COOKIE_ATTRIBUTE_SEPARATOR).append(DOMAIN).append(EQUALS).append(domain);
        }
        if (hasMaxAge()) {
            builder.append(COOKIE_ATTRIBUTE_SEPARATOR).append(MAX_AGE).append(EQUALS).append(maxAge);
        }
        if (isSecured()) {
            builder.append(COOKIE_ATTRIBUTE_SEPARATOR).append(SECURE);
        }
        if (isHttpOnly()) {
            builder.append(COOKIE_ATTRIBUTE_SEPARATOR).append(HTTP_ONLY);
        }
        if (hasExpiryDate()) {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            builder.append(COOKIE_ATTRIBUTE_SEPARATOR).append(EXPIRES).append(EQUALS).append(simpleDateFormat.format(expiryDate));
        }
        if (hasVersion()) {
            builder.append(COOKIE_ATTRIBUTE_SEPARATOR).append(VERSION).append(EQUALS).append(version);
        }
        return builder.toString();
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

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setSecured(boolean secured) {
        this.secured = secured;
    }

    public void setHttpOnly(boolean httpOnly) {
        this.httpOnly = httpOnly;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public static Cookie parse(String rawCookie) {

        System.out.println(rawCookie);
        String[] rawCookieParams = rawCookie.split(";");
        System.out.println(Arrays.toString(rawCookieParams));
        String[] rawCookieKeyAndValue = rawCookieParams[0].split("=", 2);
        if (rawCookieKeyAndValue.length != 2) {
            return null;
        }
        String cookieKey = rawCookieKeyAndValue[0].trim();
        String cookieValue = rawCookieKeyAndValue[1].trim();
        Cookie cookie = new Cookie(cookieKey, cookieValue);
        for (int i = 1; i < rawCookieParams.length; i++) {
            String rawCookieParamKeyAndValue[] = rawCookieParams[i].trim().split("=", 2);
            System.out.println("raw key and value : " + Arrays.toString(rawCookieParamKeyAndValue));
            String paramKey = rawCookieParamKeyAndValue[0].trim();

            if (paramKey.equalsIgnoreCase(SECURE)) {
                cookie.setSecured(true);
            } else if (paramKey.equalsIgnoreCase(HTTP_ONLY)) {
                cookie.setHttpOnly(true);
            } else {
                if (rawCookieParamKeyAndValue.length < 2) {
                    continue;
                }
                String paramValue = rawCookieParamKeyAndValue[1].trim();
                if (paramKey.equalsIgnoreCase(EXPIRES)) {
                    try {
                        SimpleDateFormat format = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss zzz");
                        Date expiryDate = format.parse(paramValue);
                        cookie.setExpiryDate(expiryDate);
                    } catch (ParseException ex) {
                        Logger.getLogger(Cookie.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (paramKey.equalsIgnoreCase(MAX_AGE)) {
                    long maxAge = Long.parseLong(paramValue);
                    Date expiryDate = new Date(System.currentTimeMillis() + maxAge);
                    cookie.setExpiryDate(expiryDate);
                } else if (paramKey.equalsIgnoreCase(DOMAIN)) {
                    cookie.setDomain(paramValue);
                } else if (paramKey.equalsIgnoreCase(PATH)) {
                    cookie.setPath(paramValue);
                } else if (paramKey.equalsIgnoreCase(COMMENT)) {
                    cookie.setPath(paramValue);
                }
            }
        }

        return cookie;
    }
    public static Cookie parse(JSONObject obj) throws JSONException {
        Cookie c = new Cookie();
        Iterator it = obj.keys();
        
        while (it.hasNext()) {
            String key = (String)it.next();
            Object value = obj.get(key);
            if("key".equalsIgnoreCase(key)) {
                c.setKey((String)value);
            }
            else if("value".equalsIgnoreCase(key)) {
                c.setValue((String)value);
            }
            else if(PATH.equalsIgnoreCase(key)) {
                c.setPath((String)value);
            }
            else if(COMMENT.equalsIgnoreCase(key)) {
                c.setComment((String)value);
            }
            else if(DOMAIN.equalsIgnoreCase(key)) {
                c.setDomain((String)value);
            }
            else if(MAX_AGE.equalsIgnoreCase(key)) {
                c.setMaxAge((Integer)value);
            }
            else if(HTTP_ONLY.equalsIgnoreCase(key)) {
                c.setHttpOnly((Boolean)value);
            }
            else if(SECURE.equalsIgnoreCase(key)) {
                c.setSecured((Boolean)value);
            }
            else if(VERSION.equalsIgnoreCase(key)) {
                c.setVersion((Integer)value);
            }
            else if(EXPIRES.equalsIgnoreCase(key)) {
                c.setExpiryDate((Date)value);
            }
          
        }
        return c;
        
    }
}
