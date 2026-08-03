package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.v2_1.structure.Attribute;

public class AttributeBenchmarkSubject extends KmipBenchmarkSubject<Attribute> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public AttributeBenchmarkSubject() throws Exception {
    Attribute subject = Attribute
        .builder()
        .vendorIdentification(org.purpleBean.kmip.model.core.type.VendorIdentification.of("vendor"))
        .attributeName(org.purpleBean.kmip.model.core.type.AttributeName.of("TestAttr"))
        .build();
    initialize(subject, Attribute.class);
  }

  @Override
  public String name() {
    return "Attribute";
  }
}