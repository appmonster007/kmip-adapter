package org.purpleBean.kmip.benchmark.subjects.model.v2x1.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2x1.structure.AttributeReference;

public class AttributeReferenceBenchmarkSubject extends KmipBenchmarkSubject<AttributeReference> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public AttributeReferenceBenchmarkSubject() throws Exception {
    AttributeReference subject = AttributeReference
        .builder()
        .build();
    initialize(subject, AttributeReference.class);
  }

  @Override
  public String name() {
    return "AttributeReference";
  }
}