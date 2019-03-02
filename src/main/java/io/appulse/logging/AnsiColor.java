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

import static lombok.AccessLevel.PRIVATE;

import lombok.experimental.FieldDefaults;

/**
 * {@link AnsiElement Ansi} colors.
 *
 * @author Artem Labazin
 * @since 1.0.0
 */
@FieldDefaults(level = PRIVATE, makeFinal = true)
public enum AnsiColor implements AnsiElement {

  /**
   * System default ANSI color.
   */
  DEFAULT("39"),

  /**
   * Black ANSI color.
   */
  BLACK("30"),

  /**
   * Red ANSI color.
   */
  RED("31"),

  /**
   * Green ANSI color.
   */
  GREEN("32"),

  /**
   * Yellow ANSI color.
   */
  YELLOW("33"),

  /**
   * Blue ANSI color.
   */
  BLUE("34"),

  /**
   * Magenta ANSI color.
   */
  MAGENTA("35"),

  /**
   * Cyan ANSI color.
   */
  CYAN("36"),

  /**
   * White ANSI color.
   */
  WHITE("37"),

  /**
   * High bntensty black ANSI color.
   */
  BRIGHT_BLACK("90"),

  /**
   * High bntensty red ANSI color.
   */
  BRIGHT_RED("91"),

  /**
   * High bntensty green ANSI color.
   */
  BRIGHT_GREEN("92"),

  /**
   * High bntensty yellow ANSI color.
   */
  BRIGHT_YELLOW("93"),

  /**
   * High bntensty blue ANSI color.
   */
  BRIGHT_BLUE("94"),

  /**
   * High bntensty magenta ANSI color.
   */
  BRIGHT_MAGENTA("95"),

  /**
   * High bntensty cyan ANSI color.
   */
  BRIGHT_CYAN("96"),

  /**
   * High bntensty white ANSI color.
   */
  BRIGHT_WHITE("97");

  String code;

  AnsiColor (String code) {
    this.code = code;
  }

  @Override
  public String toString () {
    return code;
  }
}
