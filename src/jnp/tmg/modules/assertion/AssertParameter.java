/**
*
* AssertParameter 
* Created by @minht on Oct 14, 2018
*/

package jnp.tmg.modules.assertion;

/**
 *
 * @author minht
 */
public class AssertParameter {

  public static <T> T notNull(T object, Class aClass) {
    return notNull(object, aClass.getSimpleName());
  }

  public static <T> T notNull(T object, String parameterName) {
    if (object == null) {
      throw new IllegalArgumentException("$parameterName cannot be null");
    }
    return object;
  }
}
