package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.structure.Attribute;

public class AttributeBenchmarkSubject extends KmipBenchmarkSubject<Attribute> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public AttributeBenchmarkSubject() throws Exception {
    Attribute subject = Attribute
        .builder()
        .vendorIdentification(org.purplebean.kmip.model.core.type.VendorIdentification.of("vendor"))
        .attributeName(org.purplebean.kmip.model.core.type.AttributeName.of("TestAttr"))
        .build();
    initialize(subject, Attribute.class);
  }

  @Override
  public String name() {
    return "Attribute";
  }
}