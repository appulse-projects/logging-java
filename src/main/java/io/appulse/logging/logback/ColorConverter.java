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

package io.appulse.logging.logback;

import static ch.qos.logback.classic.Level.ERROR_INTEGER;
import static ch.qos.logback.classic.Level.WARN_INTEGER;
import static io.appulse.logging.AnsiColor.BLUE;
import static io.appulse.logging.AnsiColor.CYAN;
import static io.appulse.logging.AnsiColor.GREEN;
import static io.appulse.logging.AnsiColor.MAGENTA;
import static io.appulse.logging.AnsiColor.RED;
import static io.appulse.logging.AnsiColor.YELLOW;
import static io.appulse.logging.AnsiStyle.FAINT;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import io.appulse.logging.AnsiElement;
import io.appulse.logging.AnsiOutput;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.CompositeConverter;

/**
 * Logback {@link CompositeConverter} colors output using the {@link AnsiOutput} class. A
 * single 'color' option can be provided to the converter, or if not specified color will
 * be picked based on the logging level.
 *
 * @author Artem Labazin
 * @since 1.0.0
 */
public class ColorConverter extends CompositeConverter<ILoggingEvent> {

  private static final Map<String, AnsiElement> ELEMENTS;

  static {
    Map<String, AnsiElement> ansiElements = new HashMap<>();
    ansiElements.put("faint", FAINT);
    ansiElements.put("red", RED);
    ansiElements.put("green", GREEN);
    ansiElements.put("yellow", YELLOW);
    ansiElements.put("blue", BLUE);
    ansiElements.put("magenta", MAGENTA);
    ansiElements.put("cyan", CYAN);

    ELEMENTS = Collections.unmodifiableMap(ansiElements);
  }

  private static final Map<Integer, AnsiElement> LEVELS;

  static {
    Map<Integer, AnsiElement> ansiLevels = new HashMap<>();
    ansiLevels.put(ERROR_INTEGER, RED);
    ansiLevels.put(WARN_INTEGER, YELLOW);
    LEVELS = Collections.unmodifiableMap(ansiLevels);
  }

  @Override
  protected String transform (ILoggingEvent event, String in) {
    AnsiElement element = ELEMENTS.get(getFirstOption());
    if (element == null) {
      // Assume highlighting
      element = LEVELS.get(event.getLevel().toInteger());
      element = element == null
                ? GREEN
                : element;
    }
    return toAnsiString(in, element);
  }

  /**
   * Returns ANSI string representation.
   *
   * @param in input string.
   * @param element ANSI element.
   *
   * @return ANSI input string representation.
   */
  protected String toAnsiString (String in, AnsiElement element) {
    return AnsiOutput.toString(element, in);
  }
}
