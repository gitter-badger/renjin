/*
 * Renjin : JVM-based interpreter for the R language for the statistical analysis
 * Copyright © 2010-${year} BeDataDriven Groep B.V. and contributors
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, a copy is available at
 *  https://www.gnu.org/licenses/gpl-2.0.txt
 *
 */

package org.renjin.gcc.codegen.vptr;

import org.renjin.gcc.codegen.MethodGenerator;
import org.renjin.gcc.codegen.array.FatArrayExpr;
import org.renjin.gcc.codegen.expr.ArrayExpr;
import org.renjin.gcc.codegen.expr.Expressions;
import org.renjin.gcc.codegen.expr.GExpr;
import org.renjin.gcc.codegen.expr.JExpr;
import org.renjin.gcc.codegen.type.UnsupportedCastException;
import org.renjin.gcc.codegen.type.fun.FunPtr;
import org.renjin.gcc.codegen.type.primitive.PrimitiveValue;
import org.renjin.gcc.codegen.type.record.RecordArrayExpr;
import org.renjin.gcc.codegen.type.voidt.VoidPtr;
import org.renjin.gcc.gimple.type.GimpleArrayType;
import org.renjin.gcc.gimple.type.GimplePrimitiveType;
import org.renjin.gcc.gimple.type.GimpleType;

/**
 * An array expression backed by a Virtual Pointer.
 *
 * <p>Basically the only difference between this class and VPtrExpr is that
 * an array copies values when storing.</p>
 */
public class VArrayExpr implements ArrayExpr {

  /**
   * Reference to the {@link org.renjin.gcc.runtime.Ptr} instance backing this array.
   */
  private JExpr ref;

  private GimpleArrayType arrayType;


  public VArrayExpr(GimpleArrayType arrayType, JExpr ref) {
    this.arrayType = arrayType;
    this.ref = ref;
  }

  @Override
  public void store(MethodGenerator mv, GExpr rhs) {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public GExpr addressOf() {
    throw new UnsupportedOperationException("TODO");
  }


  @Override
  public FunPtr toFunPtr() throws UnsupportedCastException {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public FatArrayExpr toArrayExpr() throws UnsupportedCastException {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public PrimitiveValue toPrimitiveExpr(GimplePrimitiveType targetType) throws UnsupportedCastException {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public VoidPtr toVoidPtrExpr() throws UnsupportedCastException {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public RecordArrayExpr toRecordArrayExpr() throws UnsupportedCastException {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public VPtrExpr toVPtrExpr() throws UnsupportedCastException {
    throw new UnsupportedOperationException("TODO");
  }

  @Override
  public GExpr elementAt(GimpleType expectedType, JExpr index) {
    JExpr zeroBasedIndex = Expressions.difference(index, arrayType.getLbound());
    JExpr byteOffset = Expressions.product(zeroBasedIndex, expectedType.sizeOf());
    PointerType pointerType = PointerType.ofType(expectedType);

    JExpr derefExpr = new DerefExprWithOffset(pointerType, ref, byteOffset);

    return VPtrExpr.wrap(expectedType, derefExpr);

  }
}