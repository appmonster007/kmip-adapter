package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.math.BigInteger;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.D;

/**
 * Benchmark subject for {@link D}.
 */
public class DBenchmarkSubject extends KmipBenchmarkSubject<D> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  /**
   * Constructs a new {@link DBenchmarkSubject}.
   */
  public DBenchmarkSubject() throws Exception {
    D d = D
        .builder()
        .value(BigInteger.ONE)
        .build();
    initialize(d, D.class);
  }

  @Override
  public String name() {
    return "D";
  }

}