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
    
    public Headers(List<Header> entities) {
        super(entities);
    }
    
}
