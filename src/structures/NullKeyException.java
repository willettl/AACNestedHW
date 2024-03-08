package structures;

/**
 * Exceptions that indicate that a key is null.
 *
 * @author Samuel A. Rebelsky
 */
public class NullKeyException extends Exception {
  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new exception.
   */
  public NullKeyException() {
    super("key not found");
  } // NullKeyException()

  /**
   * Create a new exception with a particular message.
   */
  public NullKeyException(String message) {
    super(message);
  } // NullKeyException(String)
} // class NullKeyException
