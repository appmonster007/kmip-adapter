package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.math.BigInteger;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.Y;

/**
 * Benchmark subject for {@link Y}.
 */
public class YBenchmarkSubject extends KmipBenchmarkSubject<Y> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link YBenchmarkSubject}.
   */
  public YBenchmarkSubject() throws Exception {
    Y y = Y
        .builder()
        .value(BigInteger.ONE)
        .build();
    initialize(y, Y.class);
  }

  @Override
  public String name() {
    return "Y";
  }

}