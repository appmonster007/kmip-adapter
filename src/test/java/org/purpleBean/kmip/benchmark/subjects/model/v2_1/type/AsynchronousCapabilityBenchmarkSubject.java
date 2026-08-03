package org.purpleBean.kmip.benchmark.subjects.model.v2_1.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.type.AsynchronousCapability;

public class AsynchronousCapabilityBenchmarkSubject
    extends KmipBenchmarkSubject<AsynchronousCapability> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public AsynchronousCapabilityBenchmarkSubject() throws Exception {
    AsynchronousCapability subject = AsynchronousCapability.of(true);
    initialize(subject, AsynchronousCapability.class);
  }

  @Override
  public String name() {
    return "AsynchronousCapability";
  }
}