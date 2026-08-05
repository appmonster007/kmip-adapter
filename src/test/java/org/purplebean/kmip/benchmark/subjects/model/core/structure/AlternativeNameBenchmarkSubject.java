package org.purplebean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.AlternativeNameType;
import org.purplebean.kmip.model.core.structure.AlternativeName;
import org.purplebean.kmip.model.core.type.AlternativeNameValue;

/**
 * Benchmark subject for {@link AlternativeName}.
 */
public class AlternativeNameBenchmarkSubject extends KmipBenchmarkSubject<AlternativeName> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link AlternativeNameBenchmarkSubject}.
   */
  public AlternativeNameBenchmarkSubject() throws Exception {
    AlternativeName alternativename = AlternativeName
        .builder()
        .alternativeNameValue(AlternativeNameValue.of("SomeAliasName"))
        .alternativeNameType(AlternativeNameType.Standard.UNINTERPRETED_TEXT_STRING.inst())
        .build();
    initialize(alternativename, AlternativeName.class);
  }

  @Override
  public String name() {
    return "AlternativeName";
  }
}