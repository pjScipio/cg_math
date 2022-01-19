package misc;

/**
 * Shared interface for all observers.
 */
public interface Observer {

  /**
   * Something has happend at the observed object.
   */
  void update();

  /**
   * More specific information about what happend.
   * @param sender This observer sent the message.
   * @param descr Identifier of the update reason
   * @param payload (Optional) Content of the update event.
   */
  default void update(Observable sender, String descr, Object payload) {
    // Default implementation: ignore
    // TODO: maybe combine with update passing an update-type and an update-payload?
  }
}
