/**
 * This file is part of the computer graphics project of the computer graphics group led by
 * Prof. Dr. Philipp Jenke at the University of applied Sciences (HAW) in Hamburg.
 */

package misc;

import java.io.PrintStream;

/**
 * Generic logger class.
 */
public class Logger {

  /**
   * Logging level
   */
  public enum Level {
    // All messages logged (debug, msg, error, exception, performance)
    ALL,
    // Only msg, error, exception
    MEDIUM,
    // Only performance
    PERFORMANCE,
    // Only error, exception
    ONLY_ERRORS
  }

  /**
   * Singleton instance.
   */
  private static Logger instance = null;

  private Level level;

  private Logger() {
    level = Level.MEDIUM;
  }

  public static Logger getInstance() {
    if (instance == null) {
      instance = new Logger();
    }
    return instance;
  }

  /**
   * Log a formatted debug message,
   * printed at level <em>ALL</em>.
   *
   * @param fmt  the message format
   * @param args zero or more arguments referenced by the message format
   * @see String#format(String, Object...)
   */
  public void debug(String fmt, Object... args) {
    if (level == Level.ALL) {
      log(System.out, fmt, args);
    }
  }

  /**
   * Log a formatted informational message,
   * printed at levels <em>ALL</em> and <em>MEDIUM</em>.
   *
   * @param fmt  the message format
   * @param args zero or more arguments referenced by the message format
   * @see String#format(String, Object...)
   */
  public void msg(String fmt, Object... args) {
    if (level == Level.ALL || level == Level.MEDIUM) {
      log(System.out, fmt, args);
    }
  }

  /**
   * Log a formatted performance message,
   * printed at levels <em>ALL</em> and <em>PERFORMANCE</em>.
   *
   * @param fmt  the message format
   * @param args zero or more arguments referenced by the message format
   * @see String#format(String, Object...)
   */
  public void performance(String fmt, Object... args) {
    if (level == Level.ALL || level == Level.PERFORMANCE) {
      log(System.out, fmt, args);
    }
  }

  /**
   * Log a formatted error message,
   * printed at any level.
   *
   * @param fmt  the message format
   * @param args zero or more arguments referenced by the message format
   * @see String#format(String, Object...)
   */
  public void error(String fmt, Object... args) {
    log(System.err, fmt, args);
  }

  /**
   * Log the occurrence of a thrown exception,
   * printed at any level.
   *
   * @param msg a possible explanation
   * @param e   the thrown exception
   */
  public void exception(String msg, Throwable e) {
    log(System.err, "Exception: %s\n %s", msg, ThrowableUtil.getCauseChainText(e));
  }


  /**
   * Log the occurrence of a thrown exception,
   * printed at any level.
   *
   * @param e the thrown exception
   */
  public void exception(Throwable e) {
    log(System.err, ThrowableUtil.getCauseChainText(e));
  }

  /**
   * Prints the formatted message to a stream. If there are no arguments given,
   * the format is printed as is (for backwards compatibility).
   */
  private void log(PrintStream stream, String fmt, Object... args) {
    if (args == null || args.length == 0) {
      stream.println(fmt);
    } else {
      stream.printf(fmt + "%n", args);
    }
  }
}
