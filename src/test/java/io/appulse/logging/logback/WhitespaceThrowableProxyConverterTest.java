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
import static org.assertj.core.api.Assertions.assertThat;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.classic.spi.ThrowableProxy;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link WhitespaceThrowableProxyConverter}.
 *
 * @author Artem Labazin
 * @since 1.0.0
 */
@FieldDefaults(level = PRIVATE, makeFinal = true)
class WhitespaceThrowableProxyConverterTest {

  private static final String LINE_SEPARATOR = System.getProperty("line.separator");

  WhitespaceThrowableProxyConverter converter = new WhitespaceThrowableProxyConverter();

  LoggingEvent event = new LoggingEvent();

  @Test
  void noStackTrace () {
    String s = converter.convert(event);
    assertThat(s).isEmpty();
  }

  @Test
  void withStackTrace () {
    event.setThrowableProxy(new ThrowableProxy(new RuntimeException()));
    String s = converter.convert(event);
    assertThat(s).startsWith(LINE_SEPARATOR);
    assertThat(s).startsWith(LINE_SEPARATOR);
    assertThat(s).endsWith(LINE_SEPARATOR);
  }
}
