package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.ProtectionStorageMask;

public class ProtectionStorageMaskBenchmarkSubject
    extends KmipBenchmarkSubject<ProtectionStorageMask> {

  @Getter
  private final KmipSpec spec = KmipSpec.V2_1;

  public ProtectionStorageMaskBenchmarkSubject() throws Exception {
    ProtectionStorageMask subject = ProtectionStorageMask.of(123);
    initialize(subject, ProtectionStorageMask.class);
  }

  @Override
  public String name() {
    return "ProtectionStorageMask";
  }
}