package org.renjin.gcc.codegen.param;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.renjin.gcc.codegen.Var;
import org.renjin.gcc.codegen.VarAllocator;
import org.renjin.gcc.codegen.expr.ExprGenerator;
import org.renjin.gcc.codegen.var.AddressablePrimitiveVarGenerator;
import org.renjin.gcc.codegen.var.PrimitiveVarGenerator;
import org.renjin.gcc.gimple.GimpleParameter;
import org.renjin.gcc.gimple.type.GimplePrimitiveType;
import org.renjin.gcc.gimple.type.GimpleType;

import java.util.Collections;
import java.util.List;

/**
 * Strategy for simple primitive parameters (e.g. double, int, etc)
 */
public class PrimitiveParamStrategy extends ParamStrategy {

  private GimplePrimitiveType type;

  public PrimitiveParamStrategy(GimpleType gimpleType) {
    this.type = (GimplePrimitiveType) gimpleType;
  }

  public GimplePrimitiveType getType() {
    return type;
  }

  @Override
  public List<Type> getParameterTypes() {
    return Collections.singletonList(type.jvmType());
  }

  @Override
  public ExprGenerator emitInitialization(MethodVisitor mv, GimpleParameter parameter, List<Var> paramVars, VarAllocator localVars) {
    PrimitiveVarGenerator var = new PrimitiveVarGenerator(type, paramVars.get(0));

    if(parameter.isAddressable()) {
      AddressablePrimitiveVarGenerator addressableVar = new AddressablePrimitiveVarGenerator(
          type, localVars.reserve(parameter.getName() + "$array", type.jvmType()));  
      
      addressableVar.emitStore(mv, var);
      return addressableVar;
    
    } else {
      // No initialization required, already set to the local variable
      return var;
    }
  }

  @Override
  public void emitPushParameter(MethodVisitor mv, ExprGenerator parameterValueGenerator) {
    parameterValueGenerator.emitPrimitiveValue(mv);
  }
}