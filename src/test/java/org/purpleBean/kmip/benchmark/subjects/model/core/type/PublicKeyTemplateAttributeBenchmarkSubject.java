package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.PublicKeyTemplateAttribute;

public class PublicKeyTemplateAttributeBenchmarkSubject
    extends KmipBenchmarkSubject<PublicKeyTemplateAttribute> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public PublicKeyTemplateAttributeBenchmarkSubject() throws Exception {
    PublicKeyTemplateAttribute subject = PublicKeyTemplateAttribute.of("default-string");
    initialize(subject, PublicKeyTemplateAttribute.class);
  }

  @Override
  public String name() {
    return "PublicKeyTemplateAttribute";
  }
}