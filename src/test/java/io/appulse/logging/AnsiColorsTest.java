/*
 * Copyright 2019 the original author or authors.
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

import static io.appulse.logging.AnsiColor.BLACK;
import static io.appulse.logging.AnsiColor.BLUE;
import static io.appulse.logging.AnsiColor.BRIGHT_BLACK;
import static io.appulse.logging.AnsiColor.BRIGHT_BLUE;
import static io.appulse.logging.AnsiColor.BRIGHT_CYAN;
import static io.appulse.logging.AnsiColor.BRIGHT_GREEN;
import static io.appulse.logging.AnsiColor.BRIGHT_MAGENTA;
import static io.appulse.logging.AnsiColor.BRIGHT_RED;
import static io.appulse.logging.AnsiColor.BRIGHT_WHITE;
import static io.appulse.logging.AnsiColor.BRIGHT_YELLOW;
import static io.appulse.logging.AnsiColor.CYAN;
import static io.appulse.logging.AnsiColor.GREEN;
import static io.appulse.logging.AnsiColor.MAGENTA;
import static io.appulse.logging.AnsiColor.RED;
import static io.appulse.logging.AnsiColor.WHITE;
import static io.appulse.logging.AnsiColor.YELLOW;
import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Color;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link AnsiColors}.
 *
 * @since 1.0.0
 * @author Artem Labazin
 */
class AnsiColorsTest {

  @Test
  void getClosestWhenExactMatchShouldReturnAnsiColor () {
    assertThat(getClosest(0x000000)).isEqualTo(BLACK);
    assertThat(getClosest(0xAA0000)).isEqualTo(RED);
    assertThat(getClosest(0x00AA00)).isEqualTo(GREEN);
    assertThat(getClosest(0xAA5500)).isEqualTo(YELLOW);
    assertThat(getClosest(0x0000AA)).isEqualTo(BLUE);
    assertThat(getClosest(0xAA00AA)).isEqualTo(MAGENTA);
    assertThat(getClosest(0x00AAAA)).isEqualTo(CYAN);
    assertThat(getClosest(0xAAAAAA)).isEqualTo(WHITE);
    assertThat(getClosest(0x555555)).isEqualTo(BRIGHT_BLACK);
    assertThat(getClosest(0xFF5555)).isEqualTo(BRIGHT_RED);
    assertThat(getClosest(0x55FF00)).isEqualTo(BRIGHT_GREEN);
    assertThat(getClosest(0xFFFF55)).isEqualTo(BRIGHT_YELLOW);
    assertThat(getClosest(0x5555FF)).isEqualTo(BRIGHT_BLUE);
    assertThat(getClosest(0xFF55FF)).isEqualTo(BRIGHT_MAGENTA);
    assertThat(getClosest(0x55FFFF)).isEqualTo(BRIGHT_CYAN);
    assertThat(getClosest(0xFFFFFF)).isEqualTo(BRIGHT_WHITE);
  }

  @Test
  void getClosestWhenCloseShouldReturnAnsiColor () {
    assertThat(getClosest(0x292424)).isEqualTo(BLACK);
    assertThat(getClosest(0x8C1919)).isEqualTo(RED);
    assertThat(getClosest(0x0BA10B)).isEqualTo(GREEN);
    assertThat(getClosest(0xB55F09)).isEqualTo(YELLOW);
    assertThat(getClosest(0x0B0BA1)).isEqualTo(BLUE);
    assertThat(getClosest(0xA312A3)).isEqualTo(MAGENTA);
    assertThat(getClosest(0x0BB5B5)).isEqualTo(CYAN);
    assertThat(getClosest(0xBAB6B6)).isEqualTo(WHITE);
    assertThat(getClosest(0x615A5A)).isEqualTo(BRIGHT_BLACK);
    assertThat(getClosest(0xF23333)).isEqualTo(BRIGHT_RED);
    assertThat(getClosest(0x55E80C)).isEqualTo(BRIGHT_GREEN);
    assertThat(getClosest(0xF5F54C)).isEqualTo(BRIGHT_YELLOW);
    assertThat(getClosest(0x5656F0)).isEqualTo(BRIGHT_BLUE);
    assertThat(getClosest(0xFA50FA)).isEqualTo(BRIGHT_MAGENTA);
    assertThat(getClosest(0x56F5F5)).isEqualTo(BRIGHT_CYAN);
    assertThat(getClosest(0xEDF5F5)).isEqualTo(BRIGHT_WHITE);
  }

  private AnsiColor getClosest (int rgb) {
    return AnsiColors.getClosest(new Color(rgb));
  }
}
