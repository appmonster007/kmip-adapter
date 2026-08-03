package org.purplebean.kmip.benchmark.subjects.model.v2x1.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.type.ProtectionStorageMask;

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