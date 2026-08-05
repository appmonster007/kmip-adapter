package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.Attribute;
import org.purplebean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.AttributeValue;

/**
 * Benchmark subject for {@link PrivateKeyTemplateAttribute}.
 */
public class PrivateKeyTemplateAttributeBenchmarkSubject
    extends KmipBenchmarkSubject<PrivateKeyTemplateAttribute> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link PrivateKeyTemplateAttributeBenchmarkSubject}.
   */
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