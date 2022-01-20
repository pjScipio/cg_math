/**
 * This file is part of the computer graphics project of the computer graphics group led by
 * Prof. Dr. Philipp Jenke at the University of applied Sciences (HAW) in Hamburg.
 */

package misc;

import java.util.HashSet;
import java.util.Set;

/**
 * Simple implementation of something observable
 */
public class Observable {

  /**
   * Set of all observers
   */
  private final Set<Observer> observers = new HashSet<>();

  /**
   * Register an additional observer
   */
  public void addObserver(Observer observer) {
    observers.add(observer);
  }

  /**
   * Inform all registered observers that something happend.
   */
  public void notifyAllObservers() {
    observers.forEach(o -> o.update(this, null, null));
  }

  /**
   * Send a debug message to all registered observers
   *
   * @param descr Identifier of the update reason
   * @param payload (Optional) Content of the update event.
   */
  public void notifyAllObservers(String descr, Object payload) {
    observers.forEach(o -> o.update(this, descr, payload));
  }
}
