package misc;


import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A special variant of {@link Random},
 * which can not be re-seeded via {@link Random#setSeed(long)}.
 * <p>
 * This class may be used to ensure deterministic results after creation
 * with the same seed.
 */
public class SeededRandom extends Random {
  private final boolean initialized;

  /**
   * Returns a new seed to be used for constructing this class.
   */
  public static long newSeed() {
    // this is save, because the TLR can not be reseeded
    return ThreadLocalRandom.current().nextLong();
  }

  public SeededRandom(long seed) {
    super(seed);
    initialized = true;
  }

  /**
   * This instance may not be re-seeded,
   * so this method just throws {@link UnsupportedOperationException}.
   */
  @Override
  public synchronized void setSeed(long seed) {
    if (initialized) {
      throw new UnsupportedOperationException();
    } else {
      super.setSeed(seed);
    }
  }
}
