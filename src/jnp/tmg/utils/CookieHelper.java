/**
 *
 * CookieHelper
 * Created by @minht on Nov 3, 2018
 */
package jnp.tmg.utils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import jnp.tmg.modules.http.Cookie;

public class CookieHelper {

    public static Cookie parseRawCookie(String rawCookie) throws Exception {

        System.out.println(rawCookie);
        String[] rawCookieParams = rawCookie.split(";");
        System.out.println(Arrays.toString(rawCookieParams));
        String[] rawCookieKeyAndValue = rawCookieParams[0].split("=",2);
        if (rawCookieKeyAndValue.length < 2) {
            throw new Exception("Invalid cookie: missing name and value.");
        }
        String cookieKey = rawCookieKeyAndValue[0].trim();
        String cookieValue = rawCookieKeyAndValue[1].trim();
        Cookie cookie = new Cookie(cookieKey, cookieValue);
        for (int i = 1; i < rawCookieParams.length; i++) {
            String rawCookieParamKeyAndValue[] = rawCookieParams[i].trim().split("=");

            System.out.println("raw key and value : " + Arrays.toString(rawCookieParamKeyAndValue));
            String paramKey = rawCookieParamKeyAndValue[0].trim();

            if (paramKey.equalsIgnoreCase("secure")) {
                cookie.setSecured(true);
            } else if (paramKey.equalsIgnoreCase("httponly")) {
                cookie.setHttpOnly(true);
            } else {
                if (rawCookieParamKeyAndValue.length < 2) {
                    throw new Exception("Invalid cookie: attribute not a flag or missing value.");
                }

                String paramValue = rawCookieParamKeyAndValue[1].trim();

                if (paramKey.equalsIgnoreCase("expires")) {
                    SimpleDateFormat format = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss zzz");
                    Date expiryDate = format.parse(paramValue);

                    cookie.setExpiryDate(expiryDate);
                } else if (paramKey.equalsIgnoreCase("max-age")) {
                    long maxAge = Long.parseLong(paramValue);
                    Date expiryDate = new Date(System.currentTimeMillis() + maxAge);
                    cookie.setExpiryDate(expiryDate);
                } else if (paramKey.equalsIgnoreCase("domain")) {
                    cookie.setDomain(paramValue);
                } else if (paramKey.equalsIgnoreCase("path")) {
                    cookie.setPath(paramValue);
                } else if (paramKey.equalsIgnoreCase("comment")) {
                    cookie.setPath(paramValue);
                } else {
                    throw new Exception("Invalid cookie: invalid attribute name.");
                }
            }
        }

        return cookie;
    }
}
