/**
*
* HttpHeaders 
* Created by @minht on Oct 14, 2018
*/

package jnp.tmg.modules.http;

import java.util.List;
import jnp.tmg.common.MultiValueEntity;

/**
 *
 * @author minht
 */
public class Headers extends MultiValueEntity<Header>{
    
    public Headers() {
        super();
    }
    public Headers(List<Header> entities) {
        super(entities);
    }
    
    @Override
    public String toString() {
        StringBuilder headersBuilder = new StringBuilder();
        
        for(Header h : entities) {
            headersBuilder.append(h);
            headersBuilder.append("\r\n");
        }
        return headersBuilder.toString();
    }
    
}
