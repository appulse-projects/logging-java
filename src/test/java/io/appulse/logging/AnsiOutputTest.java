/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.appulse.logging;

import static io.appulse.logging.AnsiOutput.Enabled.ALWAYS;
import static io.appulse.logging.AnsiOutput.Enabled.DETECT;
import static org.junit.Assert.assertEquals;

import io.appulse.logging.AnsiOutput.Enabled;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for {@link AnsiOutput}.
 *
 * @since 1.0.0
 * @author Artem Labazin
 */
public class AnsiOutputTest {

  /**
   * Initialize {@link AnsiOutput} with {@link Enabled#ALWAYS}.
   */
  @BeforeClass
  public static void beforeClass () {
    AnsiOutput.setEnabled(ALWAYS);
  }

  /**
   * Returns {@link AnsiOutput} state to default value - {@link Enabled#DETECT}.
   */
  @AfterClass
  public static void afterClass () {
    AnsiOutput.setEnabled(DETECT);
  }

  @Test
  public void encoding () {
    String encoded = AnsiOutput.toString(
        "A", AnsiColor.RED, AnsiStyle.BOLD,
        "B", AnsiStyle.NORMAL,
        "D", AnsiColor.GREEN,
        "E", AnsiStyle.FAINT,
        "F"
    );
    assertEquals("A[31;1mB[0mD[32mE[2mF[0;39m", encoded);
  }
}
