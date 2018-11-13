/**
*
* CookiesHolder 
* Created by @minht on Nov 11, 2018
*/

package jnp.tmg.client;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jnp.tmg.modules.http.Cookie;
import jnp.tmg.modules.http.Cookies;


public class CookiesManager {
    private final Cookies cookies;
    
    private static final CookiesManager instance = new CookiesManager();
    
    private CookiesManager() {
        cookies = new Cookies(new LinkedList<>());
    }
    
    public static CookiesManager getInstance() {
        return instance;
    }
    private void loadCookiesFromFile() {
        
    }
    Cookies getCookiesByDomain(String domain) {
        List<Cookie> listCookie = new LinkedList<>();
        for(Cookie c : cookies) {
            if(domain.equals(c.getDomain())) {
                listCookie.add(c);
            }
        }
        return new Cookies(listCookie);
    }
    
    public void addCookie(String cookie) {
        try {
            cookies.addEntity(Cookie.parse(cookie));
        } catch (ParseException ex) {
            Logger.getLogger(CookiesManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addCookie(Cookie cookie) {
        cookies.addEntity(cookie);
    }
    
    public void addCookies(Cookie... cookies) {
        this.cookies.addCookies(cookies);
    }
    public void addCookies(List<Cookie> cookies) {
        this.cookies.addCookies(cookies);
    }
    public Cookies getCookies() {
        return cookies;
    }
    
    
}
