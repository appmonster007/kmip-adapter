package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.IVCounterNonce;

public class IVCounterNonceBenchmarkSubject extends KmipBenchmarkSubject<IVCounterNonce> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public IVCounterNonceBenchmarkSubject() throws Exception {
    IVCounterNonce iVCounterNonce = IVCounterNonce.of(new byte[] {0x01, 0x02, 0x03});
    initialize(iVCounterNonce, IVCounterNonce.class);
  }

  @Override
  public String name() {
    return "IVCounterNonce";
  }

}