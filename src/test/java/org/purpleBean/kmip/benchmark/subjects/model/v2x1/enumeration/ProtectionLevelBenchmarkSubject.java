package org.purpleBean.kmip.benchmark.subjects.model.v2x1.enumeration;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.enumeration.ProtectionLevel;

public class ProtectionLevelBenchmarkSubject extends KmipBenchmarkSubject<ProtectionLevel> {

  public ProtectionLevelBenchmarkSubject() throws Exception {
    ProtectionLevel protectionLevel = ProtectionLevel.Standard.HIGH.inst();
    initialize(protectionLevel, ProtectionLevel.class);
  }

  @Override
  public String name() {
    return "ProtectionLevel";
  }

}
