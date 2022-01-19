package ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMyMath {

  @Test
  public void testGetWert() {
    assertEquals(23, new MyUI().getWert());
  }
}
