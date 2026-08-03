package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.DigestValue;

public class DigestValueBenchmarkSubject extends KmipBenchmarkSubject<DigestValue> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public DigestValueBenchmarkSubject() throws Exception {
    DigestValue digestValue = DigestValue.of(new byte[] {0x01, 0x02, 0x03});
    initialize(digestValue, DigestValue.class);
  }

  @Override
  public String name() {
    return "DigestValue";
  }

}