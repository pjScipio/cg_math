/**
 * This file is part of the computer graphics project of the computer graphics group led by
 * Prof. Dr. Philipp Jenke at the University of applied Sciences (HAW) in Hamburg.
 */

package misc;

/**
 * Shared interface for all observers.
 */
public interface Observer {
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
