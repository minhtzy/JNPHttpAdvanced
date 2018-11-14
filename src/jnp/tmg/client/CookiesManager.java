package jnp.tmg.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jnp.tmg.modules.http.Cookie;
import jnp.tmg.modules.http.Cookies;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CookiesManager {

    private final Cookies cookies;
    private static final String COOKIE_PATH = "httpadvancedcookies.cookie";
    private static CookiesManager instance;

    private CookiesManager() {
        cookies = new Cookies(new LinkedList<>());
        loadCookiesFromFile();
    }

    public static CookiesManager getInstance() {
        if (instance == null) {
            synchronized (CookiesManager.class) {
                if (null == instance) {
                    instance = new CookiesManager();
                }
            }
        }
        return instance;
    }

    private void loadCookiesFromFile() {
        System.out.println("LOAD JSON FROM FILE");
        try (FileReader fr = new FileReader(COOKIE_PATH);
                BufferedReader br = new BufferedReader(fr)) {
            StringBuilder buffer = new StringBuilder();
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                buffer.append(currentLine);
            }
            JSONArray jArray = new JSONArray(buffer.toString());
            for (int i = 0; i < jArray.length(); ++i) {
                JSONObject obj = (JSONObject) jArray.get(i);
                Cookie c = Cookie.parse(obj);
                cookies.addEntity(c);
            }
            System.out.println("LOAD JSON COMPLETE");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CookiesManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CookiesManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(CookiesManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Cookies getCookiesByDomain(String domain) {
        List<Cookie> listCookie = new LinkedList<>();
        for (Cookie c : cookies) {
            if (domain.equals(c.getDomain())) {
                listCookie.add(c);
            }
        }
        return new Cookies(listCookie);
    }

    public void addCookie(String cookie) {
        addCookie(Cookie.parse(cookie));
    }

    public void addCookie(Cookie cookie) {
        if (cookie != null) {
            //cookies.getEntities().removeIf(c -> c.getKey().equals(cookie.getKey()));
            cookies.addEntity(cookie);
            writeCookieToFile();
        }

    }

    public void addCookies(Cookie... cookies) {
        addCookies(Arrays.asList(cookies));
    }

    public void addCookies(List<Cookie> cookies) {
        for (Cookie c : cookies) {
            addCookie(c);
        }
    }

    public Cookies getCookies() {
        return cookies;
    }

    private void writeCookieToFile() {
        JSONArray jsonArray = new JSONArray();

        for (Cookie c : cookies.getEntities()) {
            JSONObject child = new JSONObject(c);
            jsonArray.put(child);
        }

        try (FileWriter fw = new FileWriter(new File(COOKIE_PATH))) {
            fw.write(jsonArray.toString(4));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CookiesManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CookiesManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(CookiesManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
