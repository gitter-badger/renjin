package org.renjin.compiler.pipeline.accessor;

import org.junit.Test;
import org.renjin.compiler.pipeline.MultiThreadedVectorPipeliner;
import org.renjin.primitives.summary.DeferredMean;
import org.renjin.sexp.AttributeMap;
import org.renjin.sexp.IntBufferVector;
import org.renjin.sexp.Vector;

import java.nio.IntBuffer;
import java.util.concurrent.Executors;

public class IntBufferAccessorTest {

  @Test
  public void test() {
    int[] array = new int[100];
    for(int i=0;i<array.length;++i) {
      array[i] = i;
    }
    IntBuffer buffer = IntBuffer.wrap(array);
    IntBufferVector vector = new IntBufferVector(buffer, array.length);

    DeferredMean sum = new DeferredMean(vector, AttributeMap.EMPTY);

    MultiThreadedVectorPipeliner pipeliner = new MultiThreadedVectorPipeliner(Executors.newFixedThreadPool(1));
    Vector result = pipeliner.materialize(sum);

    System.out.println(result);
  }

}