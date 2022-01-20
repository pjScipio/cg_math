/**
 * This file is part of the computer graphics project of the computer graphics group led by
 * Prof. Dr. Philipp Jenke at the University of applied Sciences (HAW) in Hamburg.
 */

package base;

import misc.Observable;

/**
 * These parameters represent the input to the PCG system.
 */
public abstract class Parameters extends Observable {

  /**
   * This id is used to identify debug or status update events
   */
  public final static String DESCR_DEBUG_STATUS = "DESCR_DEBUG_STATUS";
}
