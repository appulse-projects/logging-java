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

import static lombok.AccessLevel.PRIVATE;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import io.appulse.logging.AnsiOutput;

import ch.qos.logback.classic.Level;
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

  @BeforeClass
  public static void setupAnsi () {
    AnsiOutput.setEnabled(AnsiOutput.Enabled.ALWAYS);
  }

  @AfterClass
  public static void resetAnsi () {
    AnsiOutput.setEnabled(AnsiOutput.Enabled.DETECT);
  }

  @Before
  public void setup () {
    this.converter = new ColorConverter();
    this.event = new LoggingEvent();
  }

  @Test
  public void faint () {
    this.converter.setOptionList(Collections.singletonList("faint"));
    String out = this.converter.transform(this.event, this.in);
    assertEquals("\033[2min\033[0;39m", out);
  }

  @Test
  public void red () {
    this.converter.setOptionList(Collections.singletonList("red"));
    String out = this.converter.transform(this.event, this.in);
    assertEquals("\033[31min\033[0;39m", out);
  }

  @Test
  public void green () {
    this.converter.setOptionList(Collections.singletonList("green"));
    String out = this.converter.transform(this.event, this.in);
    assertEquals("\033[32min\033[0;39m", out);
  }

  @Test
  public void yellow () {
    this.converter.setOptionList(Collections.singletonList("yellow"));
    String out = this.converter.transform(this.event, this.in);
    assertEquals("\033[33min\033[0;39m", out);
  }

  @Test
  public void blue () {
    this.converter.setOptionList(Collections.singletonList("blue"));
    String out = this.converter.transform(this.event, this.in);
    assertEquals("\033[34min\033[0;39m", out);
  }

  @Test
  public void magenta () {
    this.converter.setOptionList(Collections.singletonList("magenta"));
    String out = this.converter.transform(this.event, this.in);
    assertEquals("\033[35min\033[0;39m", out);
  }

  @Test
  public void cyan () {
    this.converter.setOptionList(Collections.singletonList("cyan"));
    String out = this.converter.transform(this.event, this.in);
    assertEquals("\033[36min\033[0;39m", out);
  }

  @Test
  public void highlightError () {
    this.event.setLevel(Level.ERROR);
    String out = this.converter.transform(this.event, this.in);
    assertEquals("\033[31min\033[0;39m", out);
  }

  @Test
  public void highlightWarn () {
    this.event.setLevel(Level.WARN);
    String out = this.converter.transform(this.event, this.in);
    assertEquals("\033[33min\033[0;39m", out);
  }

  @Test
  public void highlightDebug () {
    this.event.setLevel(Level.DEBUG);
    String out = this.converter.transform(this.event, this.in);
    assertEquals("\033[32min\033[0;39m", out);
  }

  @Test
  public void highlightTrace () {
    this.event.setLevel(Level.TRACE);
    String out = this.converter.transform(this.event, this.in);
    assertEquals("\033[32min\033[0;39m", out);
  }
}
