package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.Attribute;
import org.purplebean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.AttributeValue;

/**
 * Benchmark subject for {@link PublicKeyTemplateAttribute}.
 */
public class PublicKeyTemplateAttributeBenchmarkSubject
    extends KmipBenchmarkSubject<PublicKeyTemplateAttribute> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link PublicKeyTemplateAttributeBenchmarkSubject}.
   */
  public PublicKeyTemplateAttributeBenchmarkSubject() throws Exception {
    PublicKeyTemplateAttribute subject = PublicKeyTemplateAttribute
        .builder()
        .attribute(Attribute
            .builder()
            .attributeName(AttributeName.of("test-attribute"))
            .attributeValue(AttributeValue.ofInteger(1))
            .build())
        .build();
    initialize(subject, PublicKeyTemplateAttribute.class);
  }

  @Override
  public String name() {
    return "PublicKeyTemplateAttribute";
  }

}