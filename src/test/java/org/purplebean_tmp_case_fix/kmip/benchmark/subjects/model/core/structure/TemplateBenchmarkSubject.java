package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.structure.Attribute;
import org.purplebean.kmip.model.core.structure.Template;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.AttributeValue;

public class TemplateBenchmarkSubject extends KmipBenchmarkSubject<Template> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public TemplateBenchmarkSubject() throws Exception {
    Template subject = Template
        .builder()
        .attribute(Attribute
            .builder()
            .attributeName(AttributeName.of("test-attribute"))
            .attributeValue(AttributeValue.ofInteger(1))
            .build())
        .build();
    initialize(subject, Template.class);
  }

  @Override
  public String name() {
    return "Template";
  }

}