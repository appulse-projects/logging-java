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
import static java.util.Optional.ofNullable;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import io.appulse.logging.AnsiElement;
import io.appulse.logging.AnsiOutput;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.CompositeConverter;
import lombok.val;

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

  private static final Map<Integer, AnsiElement> LEVELS;

  static {
    ELEMENTS = new ConcurrentHashMap<>();
    ELEMENTS.put("faint", FAINT);
    ELEMENTS.put("red", RED);
    ELEMENTS.put("green", GREEN);
    ELEMENTS.put("yellow", YELLOW);
    ELEMENTS.put("blue", BLUE);
    ELEMENTS.put("magenta", MAGENTA);
    ELEMENTS.put("cyan", CYAN);

    LEVELS = new ConcurrentHashMap<>();
    LEVELS.put(ERROR_INTEGER, RED);
    LEVELS.put(WARN_INTEGER, YELLOW);
  }

  @Override
  protected String transform (ILoggingEvent event, String in) {
    val element = ofNullable(getFirstOption())
        .map(ELEMENTS::get)
        .filter(Objects::nonNull)
        .orElseGet(() -> ofNullable(event.getLevel().toInteger())
            .map(LEVELS::get)
            .filter(Objects::nonNull)
            .orElse(GREEN)
        );

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
