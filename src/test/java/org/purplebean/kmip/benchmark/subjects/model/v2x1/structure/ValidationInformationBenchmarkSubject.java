package org.purplebean.kmip.benchmark.subjects.model.v2x1.structure;

import lombok.Getter;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purplebean.kmip.model.core.enumeration.ValidationAuthorityType;
import org.purplebean.kmip.model.core.enumeration.ValidationType;
import org.purplebean.kmip.model.v2x1.structure.ValidationInformation;

public class ValidationInformationBenchmarkSubject
    extends KmipBenchmarkSubject<ValidationInformation> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public ValidationInformationBenchmarkSubject() throws Exception {
    ValidationInformation subject =
        ValidationInformation.of(ValidationAuthorityType.Standard.UNSPECIFIED.inst(),
            org.purplebean.kmip.model.v2x1.type.ValidationVersionMajor.of(1),
            ValidationType.Standard.UNSPECIFIED.inst(),
            org.purplebean.kmip.model.v2x1.type.ValidationLevel.of(1));
    initialize(subject, ValidationInformation.class);
  }

  @Override
  public String name() {
    return "ValidationInformation";
  }
}