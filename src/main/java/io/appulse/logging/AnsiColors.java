/*
 * Copyright 2020 the original author or authors.
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
import static java.awt.color.ColorSpace.CS_CIEXYZ;

import java.awt.Color;
import java.awt.color.ColorSpace;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import lombok.NonNull;

/**
 * Utility for working with {@link AnsiColor} in the context of {@link Color AWT Colors}.
 *
 * @since 1.0.0
 * @author Artem Labazin
 */
final class AnsiColors {

  private static final Map<AnsiColor, LabColor> ANSI_COLOR_MAP;

  static {
    ANSI_COLOR_MAP = new ConcurrentHashMap<>();
    ANSI_COLOR_MAP.put(BLACK, new LabColor(0x000000));
    ANSI_COLOR_MAP.put(RED, new LabColor(0xAA0000));
    ANSI_COLOR_MAP.put(GREEN, new LabColor(0x00AA00));
    ANSI_COLOR_MAP.put(YELLOW, new LabColor(0xAA5500));
    ANSI_COLOR_MAP.put(BLUE, new LabColor(0x0000AA));
    ANSI_COLOR_MAP.put(MAGENTA, new LabColor(0xAA00AA));
    ANSI_COLOR_MAP.put(CYAN, new LabColor(0x00AAAA));
    ANSI_COLOR_MAP.put(WHITE, new LabColor(0xAAAAAA));
    ANSI_COLOR_MAP.put(BRIGHT_BLACK, new LabColor(0x555555));
    ANSI_COLOR_MAP.put(BRIGHT_RED, new LabColor(0xFF5555));
    ANSI_COLOR_MAP.put(BRIGHT_GREEN, new LabColor(0x55FF00));
    ANSI_COLOR_MAP.put(BRIGHT_YELLOW, new LabColor(0xFFFF55));
    ANSI_COLOR_MAP.put(BRIGHT_BLUE, new LabColor(0x5555FF));
    ANSI_COLOR_MAP.put(BRIGHT_MAGENTA, new LabColor(0xFF55FF));
    ANSI_COLOR_MAP.put(BRIGHT_CYAN, new LabColor(0x55FFFF));
    ANSI_COLOR_MAP.put(BRIGHT_WHITE, new LabColor(0xFFFFFF));
  }

  private AnsiColors () {
  }

  static AnsiColor getClosest (Color color) {
    return getClosest(new LabColor(color));
  }

  private static AnsiColor getClosest (LabColor color) {
    AnsiColor result = null;
    double resultDistance = Float.MAX_VALUE;
    for (Entry<AnsiColor, LabColor> entry : ANSI_COLOR_MAP.entrySet()) {
      double distance = color.getDistance(entry.getValue());
      if (result == null || distance < resultDistance) {
        resultDistance = distance;
        result = entry.getKey();
      }
    }
    return result;
  }

  /**
   * Represents a color stored in LAB form.
   */
  private static final class LabColor {

    private static final ColorSpace XYZ_COLOR_SPACE;

    static {
      XYZ_COLOR_SPACE = ColorSpace.getInstance(CS_CIEXYZ);
    }

    private final double labValueL;

    private final double labValueA;

    private final double labValueB;

    LabColor (int rgb) {
      this(new Color(rgb));
    }

    LabColor (@NonNull Color color) {
      float[] lab = fromXyz(color.getColorComponents(XYZ_COLOR_SPACE, null));
      labValueL = lab[0];
      labValueA = lab[1];
      labValueB = lab[2];
    }

    private float[] fromXyz (float[] xyz) {
      return fromXyz(xyz[0], xyz[1], xyz[2]);
    }

    private float[] fromXyz (float x, float y, float z) {
      return new float[] {
          (float) ((function(y) - 16.0) * 116.0),
          (float) ((function(x) - function(y)) * 500.0),
          (float) ((function(y) - function(z)) * 200.0)
      };
    }

    private double function (double value) {
      return value > (216.0 / 24_389.0)
             ? Math.cbrt(value)
             : (1.0 / 3.0) * Math.pow(29.0 / 6.0, 2) * value + (4.0 / 29.0);
    }

    // See http://en.wikipedia.org/wiki/Color_difference#CIE94
    public double getDistance (LabColor other) {
      double c1 = Math.sqrt(labValueA * labValueA + labValueB * labValueB);
      double deltaC = c1 - Math.sqrt(other.labValueA * other.labValueA + other.labValueB * other.labValueB);
      double deltaA = labValueA - other.labValueA;
      double deltaB = labValueB - other.labValueB;
      double deltaH = Math.sqrt(Math.max(0.0, deltaA * deltaA + deltaB * deltaB - deltaC * deltaC));
      return Math.sqrt(Math.max(0.0,
          Math.pow((labValueL - other.labValueL) / 1.0, 2)
              + Math.pow(deltaC / (1 + 0.045 * c1), 2)
              + Math.pow(deltaH / (1 + 0.015 * c1), 2.0)));
    }
  }
}
