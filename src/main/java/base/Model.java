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
  public void update() {
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
