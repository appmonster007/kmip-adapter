package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValue;

public class PrivateKeyTemplateAttributeBenchmarkSubject
    extends KmipBenchmarkSubject<PrivateKeyTemplateAttribute> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public PrivateKeyTemplateAttributeBenchmarkSubject() throws Exception {
    PrivateKeyTemplateAttribute subject = PrivateKeyTemplateAttribute
        .builder()
        .attribute(Attribute
            .builder()
            .attributeName(AttributeName.of("test-attribute"))
            .attributeValue(AttributeValue.ofInteger(1))
            .build())
        .build();
    initialize(subject, PrivateKeyTemplateAttribute.class);
  }

  @Override
  public String name() {
    return "PrivateKeyTemplateAttribute";
  }

}