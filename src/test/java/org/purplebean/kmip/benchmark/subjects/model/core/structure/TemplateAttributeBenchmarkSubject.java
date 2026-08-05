package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.NameType;
import org.purplebean.kmip.model.core.structure.Name;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.core.type.NameValue;

/**
 * Benchmark subject for {@link TemplateAttribute}.
 */
public class TemplateAttributeBenchmarkSubject extends KmipBenchmarkSubject<TemplateAttribute> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link TemplateAttributeBenchmarkSubject}.
   */
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