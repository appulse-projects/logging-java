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

import static lombok.AccessLevel.PRIVATE;

import static org.junit.Assert.assertTrue;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.classic.spi.ThrowableProxy;
import lombok.experimental.FieldDefaults;
import org.junit.Test;

/**
 * Tests for {@link ExtendedWhitespaceThrowableProxyConverter}.
 *
 * @author Artem Labazin
 * @since 1.0.0
 */
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ExtendedWhitespaceThrowableProxyConverterTest {

  private static final String LINE_SEPARATOR = System.getProperty("line.separator");

  ExtendedWhitespaceThrowableProxyConverter converter = new ExtendedWhitespaceThrowableProxyConverter();

  LoggingEvent event = new LoggingEvent();

  @Test
  public void noStackTrace () {
    String s = this.converter.convert(this.event);
    assertTrue(s.isEmpty());
  }

  @Test
  public void withStackTrace () {
    this.event.setThrowableProxy(new ThrowableProxy(new RuntimeException()));
    String s = this.converter.convert(this.event);
    assertTrue(s.startsWith(LINE_SEPARATOR));
    assertTrue(s.endsWith(LINE_SEPARATOR));
  }
}
