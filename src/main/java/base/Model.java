/**
 * This file is part of the computer graphics project of the computer graphics group led by
 * Prof. Dr. Philipp Jenke at the University of applied Sciences (HAW) in Hamburg.
 */
package base;

import misc.Observable;
import misc.Observer;

/**
 * The model represents the abstract intermediate representation of the
 * PCG system.
 */
public abstract class Model extends Observable implements Observer {
  /**
   * The model is generated from these parameters.
   */
  private Parameters parameters;

  protected Model(Parameters parameters) {
    if (parameters != null) {
      this.parameters = parameters;
      parameters.addObserver(this);
    }
  }

  /**
   * Regenerates the model.
   * <p>
   * This method is called after the model parameters got changed.
   */
  @Override
  public void update(Observable sender, String descr, Object payload) {
  }

  /**
   * Gets the parameters this model is generated from.
   *
   * @return the parameters, or {@code null} if there are none
   */
  public Parameters getParameters() {
    return parameters;
  }
}
