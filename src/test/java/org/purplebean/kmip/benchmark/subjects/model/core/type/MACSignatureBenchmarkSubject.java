package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.MACSignature;

public class MACSignatureBenchmarkSubject extends KmipBenchmarkSubject<MACSignature> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public MACSignatureBenchmarkSubject() throws Exception {
    MACSignature mACSignature = MACSignature.of(new byte[] {0x01, 0x02, 0x03});
    initialize(mACSignature, MACSignature.class);
  }

  @Override
  public String name() {
    return "MACSignature";
  }

}