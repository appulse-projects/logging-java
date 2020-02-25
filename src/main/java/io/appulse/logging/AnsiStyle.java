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

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

/**
 * {@link AnsiElement Ansi} styles.
 *
 * @since 1.0.0
 * @author Artem Labazin
 */
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum AnsiStyle implements AnsiElement {

  /**
   * Normal font style.
   */
  NORMAL("0"),

  /**
   * Bold font style.
   */
  BOLD("1"),

  /**
   * Faint font style.
   */
  FAINT("2"),

  /**
   * Italic font style.
   */
  ITALIC("3"),

  /**
   * Underline font style.
   */
  UNDERLINE("4");

  String code;

  AnsiStyle (String code) {
    this.code = code;
  }

  @Override
  public String toString () {
    return code;
  }
}
