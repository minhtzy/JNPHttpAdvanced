/**
 *
 * Cookie
 * Created by @minht on Oct 14, 2018
 */
package jnp.tmg.modules.http;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import jnp.tmg.common.KeyAndValue;

import jnp.tmg.modules.assertion.AssertParameter;

/**
 *
 * @author minht
 */
public class Cookie implements KeyAndValue{

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

    private final String key;
    private final String value;
    private final String comment;
    private final Date expiryDate;
    private final String domain;
    private final String path;
    private final boolean secured;
    private final boolean httpOnly;
    private final int version;
    private final int maxAge;

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

    public static class Builder {

        private final String name;
        private final String value;
        private String comment;
        private Date expiryDate;
        private String domain;
        private String path;
        private boolean secured = false;
        private boolean httpOnly = false;
        private int version = UNDEFINED;
        private int maxAge = UNDEFINED;

        /**
         * Create a cookie with no value
         *
         * @param name The name of the cookie
         */
        public Builder(String name) {
            this(name, null);
        }

        /**
         * Create a cookie with name and value
         *
         * @param name The name of the cookie
         * @param value the cookie value
         */
        public Builder(String name, String value) {
            AssertParameter.notNull(name, "Cookie name");
            this.name = name;
            this.value = value;
        }

        /**
         * Set the comment describing the purpose of this cookie.
         *
         * @param comment The comment
         * @return The builder
         */
        public Builder setComment(String comment) {
            AssertParameter.notNull(name, "Cookie name");
            this.comment = comment;
            return this;
        }

        /**
         * Set the expiration {@link java.util.Date} of the cookie.
         *
         * @param date The date to set
         * @return The builder
         */
        public Builder setExpiryDate(Date date) {
            AssertParameter.notNull(date, "Cookie expiry date");
            this.expiryDate = date;
            return this;
        }

        /**
         * Set domain attribute of the cookie. The value of the Domain attribute
         * specifies the domain for which the cookie is valid.
         *
         * @param domain The domain
         * @return The builder
         */
        public Builder setDomain(String domain) {
            AssertParameter.notNull(domain, "Cookie domain");
            this.domain = domain;
            return this;
        }

        /**
         * Set the path attribute of the cookie. The value of the Path attribute
         * specifies the subset of URLs on the origin server to which this
         * cookie applies.
         *
         * @param path The path
         * @return The builder
         */
        public Builder setPath(String path) {
            AssertParameter.notNull(path, "Cookie path");
            this.path = path;
            return this;
        }

        /**
         * Set the maximum age of the cookie, specified in seconds, By default,
         * <code>-1</code> indicating the cookie will persist until browser
         * shutdown.
         *
         * @param maxAge
         * @return an integer specifying the maximum age of the cookie in
         * seconds; if negative, means the cookie persists until browser
         * shutdown
         */
        public Builder setMaxAge(int maxAge) {
            this.maxAge = maxAge;
            return this;
        }

        /**
         * Indicates to the browser whether the cookie should only be sent using
         * a secure protocol, such as HTTPS or SSL. The default value is false.
         *
         * @param secured <code>true</code> if secured
         * @return The builder
         */
        public Builder setSecured(boolean secured) {
            this.secured = secured;
            return this;
        }

        /**
         * Indicates that the cookie is only readable by the HTTP server and not
         * other API's such as JavaScript. The default value is false.
         *
         * @param httpOnly <code>true</code> if httpOnly
         * @return The builder
         */
        public Builder setHttpOnly(boolean httpOnly) {
            this.httpOnly = httpOnly;
            return this;
        }

        /**
         * Sets the version of the cookie protocol this cookie complies with.
         * Version 0 complies with the original Netscape cookie specification.
         * Version 1 complies with RFC 2109. Since RFC 2109 is still somewhat
         * new, consider version 1 as experimental; do not use it yet on
         * production sites. Parameters:
         *
         * @param version 0 if the cookie should comply with the original
         * Netscape specification; 1 if the cookie should comply with RFC 2109
         * @return The builder
         */
        public Builder setVersion(int version) {
            if (version < 0) {
                throw new IllegalArgumentException("Version cannot be less than 0");
            } else if (version > 1) {
                throw new IllegalArgumentException("Version cannot be greater than 1");
            }
            this.version = version;
            return this;
        }

        /**
         * Build a Cookie from the specified parameters.
         *
         * @return The Cookie
         */
        public Cookie build() {
            return new Cookie(name, value, comment, expiryDate, domain, path, secured, httpOnly, version, maxAge);
        }
    }
}
