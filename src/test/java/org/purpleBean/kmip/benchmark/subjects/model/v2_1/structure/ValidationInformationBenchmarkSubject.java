package org.purpleBean.kmip.benchmark.subjects.model.v2_1.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.ValidationAuthorityType;
import org.purpleBean.kmip.model.core.enumeration.ValidationType;
import org.purpleBean.kmip.model.v2_1.structure.ValidationInformation;

public class ValidationInformationBenchmarkSubject
    extends KmipBenchmarkSubject<ValidationInformation> {

  @Getter
  private final KmipSpec spec = KmipSpec.UnknownVersion;

  public ValidationInformationBenchmarkSubject() throws Exception {
    ValidationInformation subject =
        ValidationInformation.of(ValidationAuthorityType.Standard.UNSPECIFIED.inst(),
            org.purpleBean.kmip.model.v2_1.type.ValidationVersionMajor.of(1),
            ValidationType.Standard.UNSPECIFIED.inst(),
            org.purpleBean.kmip.model.v2_1.type.ValidationLevel.of(1));
    initialize(subject, ValidationInformation.class);
  }

  @Override
  public String name() {
    return "ValidationInformation";
  }
}