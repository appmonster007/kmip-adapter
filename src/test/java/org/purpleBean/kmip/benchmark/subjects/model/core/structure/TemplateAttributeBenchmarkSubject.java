package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.NameType;
import org.purpleBean.kmip.model.core.structure.Name;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.NameValue;

public class TemplateAttributeBenchmarkSubject extends KmipBenchmarkSubject<TemplateAttribute> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public TemplateAttributeBenchmarkSubject() throws Exception {
    TemplateAttribute subject = TemplateAttribute
        .builder()
        .name(Name.of(
            NameValue.of("test-name"),
            NameType.Standard.UNINTERPRETED_TEXT_STRING.inst()
        ))
        .build();
    initialize(subject, TemplateAttribute.class);
  }

  @Override
  public String name() {
    return "TemplateAttribute";
  }

}