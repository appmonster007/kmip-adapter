package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.structure.AsynchronousCorrelationValues;

public class AsynchronousCorrelationValuesBenchmarkSubject
    extends KmipBenchmarkSubject<AsynchronousCorrelationValues> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public AsynchronousCorrelationValuesBenchmarkSubject() throws Exception {
    AsynchronousCorrelationValues subject = AsynchronousCorrelationValues.of(java.util.List.of());
    initialize(subject, AsynchronousCorrelationValues.class);
  }

  @Override
  public String name() {
    return "AsynchronousCorrelationValues";
  }
}