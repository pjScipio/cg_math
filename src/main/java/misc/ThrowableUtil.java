/**
 * This file is part of the computer graphics project of the computer graphics group led by
 * Prof. Dr. Philipp Jenke at the University of applied Sciences (HAW) in Hamburg.
 */

package misc;

import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * A static utility class for throwables.
 */
public final class ThrowableUtil {
  private ThrowableUtil() {
  }

  /**
   * Creates a string containing the messages from the throwable and its cause chain.
   * <p>
   * The first throwable's message will be prepended with "Error: ",
   * the causes' messages with "Cause: ".
   */
  public static String getCauseChainText(Throwable throwable) {
    var causalChain = ExceptionUtils.getThrowableList(throwable);
    var text = new StringBuilder();
    text.append("Error: ").append(causalChain.get(0).getMessage());
    for (int i = 1; i < causalChain.size(); i++) {
      text.append("\nCause: ").append(causalChain.get(i).getMessage());
    }
    return text.toString();
  }
}
