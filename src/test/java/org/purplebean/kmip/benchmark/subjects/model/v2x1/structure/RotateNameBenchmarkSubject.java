package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.v2x1.enumeration.RotateNameType;
import org.purplebean.kmip.model.v2x1.structure.RotateName;
import org.purplebean.kmip.model.v2x1.type.RotateNameValue;

/**
 * Benchmark subject for {@link RotateName}.
 */
public class RotateNameBenchmarkSubject extends KmipBenchmarkSubject<RotateName> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  /**
   * Constructs a new {@link RotateNameBenchmarkSubject}.
   */
  public RotateNameBenchmarkSubject() throws Exception {
    RotateName subject = RotateName.of(
        RotateNameValue.of("default"),
        RotateNameType.Standard.UNINTERPRETED_TEXT_STRING.inst());
    initialize(subject, RotateName.class);
  }

  @Override
  public String name() {
    return "RotateName";
  }
}