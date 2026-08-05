package org.purplebean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.type.PrivateKeyTemplateAttribute;

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
    PrivateKeyTemplateAttribute subject = PrivateKeyTemplateAttribute.of("default-string");
    initialize(subject, PrivateKeyTemplateAttribute.class);
  }

  @Override
  public String name() {
    return "PrivateKeyTemplateAttribute";
  }
}