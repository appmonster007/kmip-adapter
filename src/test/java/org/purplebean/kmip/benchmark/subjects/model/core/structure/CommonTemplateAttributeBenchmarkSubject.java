package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.Attribute;
import org.purplebean.kmip.model.core.structure.CommonTemplateAttribute;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.AttributeValue;

public class CommonTemplateAttributeBenchmarkSubject
    extends KmipBenchmarkSubject<CommonTemplateAttribute> {

  @Getter
  private final KmipSpec spec = KmipSpec.V1_2;

  public CommonTemplateAttributeBenchmarkSubject() throws Exception {
    CommonTemplateAttribute subject = CommonTemplateAttribute
        .builder()
        .attribute(Attribute
            .builder()
            .attributeName(AttributeName.of("test-attribute"))
            .attributeValue(AttributeValue.ofInteger(1))
            .build())
        .build();
    initialize(subject, CommonTemplateAttribute.class);
  }

  @Override
  public String name() {
    return "CommonTemplateAttribute";
  }

}