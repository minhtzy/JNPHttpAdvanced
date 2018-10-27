/**
 *
 * JsonUtils
 * Created by @minht on Oct 14, 2018
 */
package jnp.tmg.utils;

import java.io.UnsupportedEncodingException;
import org.json.*;

public class JsonUtils {

    public static int PRERTTY_STRING_INDENT_FACTOR = 4;

    public static JSONObject parseJson(String rawData) throws JSONException {
        return new JSONObject(rawData);
    }

    public static JSONObject parseJson(byte[] rawData) throws UnsupportedEncodingException, JSONException {
        String strData = new String(rawData, "UTF-8");
        return new JSONObject(strData);
    }

    public static String prettyJson(String rawData) throws JSONException {
        JSONObject json = new JSONObject(rawData);
        return json.toString(PRERTTY_STRING_INDENT_FACTOR);
    }

    public static String prettyJson(byte[] rawData) throws UnsupportedEncodingException, JSONException {
        String strData = new String(rawData, "UTF-8");
        JSONObject json = new JSONObject(strData);
        return json.toString(PRERTTY_STRING_INDENT_FACTOR);
    }

}
