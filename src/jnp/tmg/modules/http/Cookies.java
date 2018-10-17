/**
*
* Cookies 
* Created by @minht on Oct 14, 2018
*/

package jnp.tmg.modules.http;

import java.util.Arrays;
import java.util.List;
import jnp.tmg.common.MultiValueEntity;
import jnp.tmg.modules.assertion.AssertParameter;

/**
 *
 * @author minht
 */
public class Cookies extends MultiValueEntity<Cookie> implements Iterable<Cookie>{
   // private final MultiValueEntity<Cookie> cookies;

    public Cookies(Cookie... cookies) {
        this(Arrays.asList(cookies));
    }

    public Cookies(List<Cookie> entities) {
        super(entities);
    }
    
    public void addCookies(Cookie... additionalCookies) {
        AssertParameter.notNull(this, "Cookie");
        entities.addAll(Arrays.asList(additionalCookies));
    }
    
    public static void addCookies(Cookies cookies , Cookie... additionalCookies) {
        cookies.addCookies(additionalCookies);
    }

}
