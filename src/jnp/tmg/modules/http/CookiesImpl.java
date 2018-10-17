/**
*
* CookiesImpl 
* Created by @minht on Oct 17, 2018
*/

package jnp.tmg.modules.http;

import java.util.List;


public class CookiesImpl extends Cookies {

    public CookiesImpl(Cookie... cookies) {
        super(cookies);
    }

    public CookiesImpl(List<Cookie> entities) {
        super(entities);
    }

}
