/**
 *
 * StringUtils
 * Created by @minht on Oct 14, 2018
 */
package jnp.tmg.utils;

public class StringUtils {

    public static boolean contains(String[] array, String valueToFind) {
        for (String element : array) {
            if (element.contains(valueToFind)) {
                return true;
            }
        }
        return false;
    }

    public static boolean endsWithIgnoreCase(String str, String searchStr) {
        if (str.isEmpty() || searchStr.isEmpty()) {
            return false;
        }
        return endsWith(str,searchStr,true);
    }

    public static boolean endsWith(String str, String searchStr, boolean ignoreCase) {
            if (str == null || searchStr == null) {
                return str == null && searchStr == null;
            }
            if (searchStr.length() > str.length()) {
                return false;
            }
            int strOffset = str.length() - searchStr.length();
            
            return str.regionMatches( ignoreCase, strOffset,searchStr,0,searchStr.length());
            
        }
}
