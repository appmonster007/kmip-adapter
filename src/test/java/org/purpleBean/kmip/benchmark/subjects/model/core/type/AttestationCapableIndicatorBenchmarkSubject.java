package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.AttestationCapableIndicator;

public class AttestationCapableIndicatorBenchmarkSubject
    extends KmipBenchmarkSubject<AttestationCapableIndicator> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public AttestationCapableIndicatorBenchmarkSubject() throws Exception {
    AttestationCapableIndicator subject = AttestationCapableIndicator.of(true);
    initialize(subject, AttestationCapableIndicator.class);
  }

  @Override
  public String name() {
    return "AttestationCapableIndicator";
  }

}