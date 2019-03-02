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

package io.appulse.logging.logback;

import static ch.qos.logback.classic.Level.DEBUG;
import static ch.qos.logback.classic.Level.ERROR;
import static ch.qos.logback.classic.Level.TRACE;
import static ch.qos.logback.classic.Level.WARN;
import static io.appulse.logging.AnsiOutput.Enabled.ALWAYS;
import static io.appulse.logging.AnsiOutput.Enabled.DETECT;
import static java.util.Collections.singletonList;
import static lombok.AccessLevel.PRIVATE;
import static org.assertj.core.api.Assertions.assertThat;

import io.appulse.logging.AnsiOutput;

import ch.qos.logback.classic.spi.LoggingEvent;
import lombok.experimental.FieldDefaults;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for {@link ColorConverter}.
 *
 * @author Artem Labazin
 * @since 1.0.0
 */
@FieldDefaults(level = PRIVATE)
public class ColorConverterTest {

  ColorConverter converter;

  LoggingEvent event;

  final String in = "in";

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

  /**
   * Re-initialize {@code converter} and {@code event}.
   */
  @Before
  public void before () {
    converter = new ColorConverter();
    event = new LoggingEvent();
  }

  @Test
  public void faint () {
    converter.setOptionList(singletonList("faint"));
    assertThat(converter.transform(event, in))
        .isEqualTo("\033[2min\033[0;39m");
  }

  @Test
  public void red () {
    converter.setOptionList(singletonList("red"));
    assertThat(converter.transform(event, in))
        .isEqualTo("\033[31min\033[0;39m");
  }

  @Test
  public void green () {
    converter.setOptionList(singletonList("green"));
    assertThat(converter.transform(event, in))
        .isEqualTo("\033[32min\033[0;39m");
  }

  @Test
  public void yellow () {
    converter.setOptionList(singletonList("yellow"));
    assertThat(converter.transform(event, in))
        .isEqualTo("\033[33min\033[0;39m");
  }

  @Test
  public void blue () {
    converter.setOptionList(singletonList("blue"));
    assertThat(converter.transform(event, in))
        .isEqualTo("\033[34min\033[0;39m");
  }

  @Test
  public void magenta () {
    converter.setOptionList(singletonList("magenta"));
    assertThat(converter.transform(event, in))
        .isEqualTo("\033[35min\033[0;39m");
  }

  @Test
  public void cyan () {
    converter.setOptionList(singletonList("cyan"));
    assertThat(converter.transform(event, in))
        .isEqualTo("\033[36min\033[0;39m");
  }

  @Test
  public void highlightError () {
    event.setLevel(ERROR);
    assertThat(converter.transform(event, in))
        .isEqualTo("\033[31min\033[0;39m");
  }

  @Test
  public void highlightWarn () {
    event.setLevel(WARN);
    assertThat(converter.transform(event, in))
        .isEqualTo("\033[33min\033[0;39m");
  }

  @Test
  public void highlightDebug () {
    event.setLevel(DEBUG);
    assertThat(converter.transform(event, in))
        .isEqualTo("\033[32min\033[0;39m");
  }

  @Test
  public void highlightTrace () {
    event.setLevel(TRACE);
    assertThat(converter.transform(event, in))
        .isEqualTo("\033[32min\033[0;39m");
  }
}
