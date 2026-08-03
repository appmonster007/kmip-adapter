package org.purpleBean.kmip.benchmark.subjects.model.v2_1.enumeration;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.enumeration.AsynchronousIndicator;

public class AsynchronousIndicatorBenchmarkSubject
    extends KmipBenchmarkSubject<AsynchronousIndicator> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public AsynchronousIndicatorBenchmarkSubject() throws Exception {
    AsynchronousIndicator subject = AsynchronousIndicator.Standard.values()[0].inst();
    initialize(subject, AsynchronousIndicator.class);
  }

  @Override
  public String name() {
    return "AsynchronousIndicator";
  }
}