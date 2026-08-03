package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.ProtectionStorageMasks;

public class ProtectionStorageMasksBenchmarkSubject
    extends KmipBenchmarkSubject<ProtectionStorageMasks> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public ProtectionStorageMasksBenchmarkSubject() throws Exception {
    ProtectionStorageMasks subject = ProtectionStorageMasks.of(java.util.List.of());
    initialize(subject, ProtectionStorageMasks.class);
  }

  @Override
  public String name() {
    return "ProtectionStorageMasks";
  }
}