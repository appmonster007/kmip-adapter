package org.purplebean.kmip.benchmark.subjects.model.core.type;

import java.math.BigInteger;
import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.G;

public class GBenchmarkSubject extends KmipBenchmarkSubject<G> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public GBenchmarkSubject() throws Exception {
    G g = G
        .builder()
        .value(BigInteger.ONE)
        .build();
    initialize(g, G.class);
  }

  @Override
  public String name() {
    return "G";
  }

}