/**
 *
 * JsonUtils
 * Created by @minht on Oct 14, 2018
 */
package jnp.tmg.utils;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public static JSONObject parseJsonFromXML(String xml) throws JSONException {
        JSONObject jsonObject = XML.toJSONObject(xml);
        return jsonObject;
    }

    public static String prettyJson(JSONObject jsonObject) {
        try {
            String prettyString = jsonObject.toString(PRERTTY_STRING_INDENT_FACTOR);
            return prettyString;
        } catch (JSONException ex) {
            Logger.getLogger(JsonUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonObject.toString();
    }

    public static String prettyJson(String rawData) {
        try {
            String data = rawData.replaceAll("[\\\\r\\\\n]+", "");
            JSONObject json = new JSONObject(data);
            return json.toString(PRERTTY_STRING_INDENT_FACTOR);
        } catch (Exception ex) {
            Logger.getLogger(JsonUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rawData;
    }

    public static String prettyJson(byte[] rawData) {
        String strData = new String(rawData);
        return prettyJson(strData);
    }

}
