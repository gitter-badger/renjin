/*
 * Renjin : JVM-based interpreter for the R language for the statistical analysis
 * Copyright © 2010-2019 BeDataDriven Groep B.V. and contributors
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, a copy is available at
 * https://www.gnu.org/licenses/gpl-2.0.txt
 */
package org.renjin.gcc.codegen.var;

import org.renjin.gcc.codegen.expr.JExpr;
import org.renjin.gcc.codegen.expr.JLValue;
import org.renjin.repackaged.asm.Type;

import java.util.HashMap;
import java.util.Map;


public class LocalStaticVarAllocator extends VarAllocator {

  private String prefix;
  private GlobalVarAllocator globalVarAllocator;
  private Map<String, Integer> nextIndex = new HashMap<>();

  public LocalStaticVarAllocator(String prefix, GlobalVarAllocator globalVarAllocator) {
    this.prefix = prefix;
    this.globalVarAllocator = globalVarAllocator;
  }

  @Override
  public JLValue reserve(String name, Type type) {
    return globalVarAllocator.reserve(uniqueName(name), type);
  }

  @Override
  public JLValue reserve(String name, Type type, JExpr initialValue) {
    return globalVarAllocator.reserve(uniqueName(name), type, initialValue);
  }

  private String uniqueName(String name) {
    // Multiple static variables with the same name can be declared in the same function
    // but in different
    String fieldName = prefix + name;
    Integer index = nextIndex.get(fieldName);
    if(index == null) {
      // Fieldname, first of it's name.
      nextIndex.put(fieldName, 2);
      return fieldName;

    } else {
      nextIndex.put(fieldName, index + 1);
      return fieldName + "$" + index;
    }
  }
}
