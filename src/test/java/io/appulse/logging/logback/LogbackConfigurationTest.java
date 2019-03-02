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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.FileAppender;
import ch.qos.logback.core.encoder.Encoder;
import ch.qos.logback.core.joran.spi.JoranException;
import org.junit.Test;

/**
 * Tests for default Logback configuration provided by {@code base.xml}.
 *
 * @since 1.0.0
 * @author Artem Labazin
 */
public class LogbackConfigurationTest {

  @Test
  public void consolePatternCanBeOverridden () throws JoranException {
    JoranConfigurator configurator = new JoranConfigurator();
    LoggerContext context = new LoggerContext();
    configurator.setContext(context);
    configurator.doConfigure(new File("src/test/resources/custom-console-log-pattern.xml"));
    Appender<ILoggingEvent> appender = context.getLogger("ROOT").getAppender("CONSOLE");
    assertTrue(appender instanceof ConsoleAppender);
    Encoder<?> encoder = ((ConsoleAppender<?>) appender).getEncoder();
    assertTrue(encoder instanceof PatternLayoutEncoder);
    assertEquals("foo", ((PatternLayoutEncoder) encoder).getPattern());
  }

  @Test
  public void filePatternCanBeOverridden () throws JoranException {
    JoranConfigurator configurator = new JoranConfigurator();
    LoggerContext context = new LoggerContext();
    configurator.setContext(context);
    configurator
        .doConfigure(new File("src/test/resources/custom-file-log-pattern.xml"));
    Appender<ILoggingEvent> appender = context.getLogger("ROOT").getAppender("FILE");
    assertTrue(appender instanceof FileAppender);
    Encoder<?> encoder = ((FileAppender<?>) appender).getEncoder();
    assertTrue(encoder instanceof PatternLayoutEncoder);
    assertEquals("bar", ((PatternLayoutEncoder) encoder).getPattern());
  }
}
