package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.math.BigInteger;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.X;

/**
 * Benchmark subject for {@link X}.
 */
public class XBenchmarkSubject extends KmipBenchmarkSubject<X> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link XBenchmarkSubject}.
   */
  public XBenchmarkSubject() throws Exception {
    X x = X
        .builder()
        .value(BigInteger.ONE)
        .build();
    initialize(x, X.class);
  }

  @Override
  public String name() {
    return "X";
  }

}