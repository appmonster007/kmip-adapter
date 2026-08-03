package org.purplebean.kmip.codec.ttlv.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.ValidationAuthorityType;
import org.purplebean.kmip.model.core.enumeration.ValidationType;
import org.purplebean.kmip.model.v2x1.structure.ValidationInformation;
import org.purplebean.kmip.model.v2x1.type.ValidationLevel;
import org.purplebean.kmip.model.v2x1.type.ValidationVersionMajor;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ValidationInformation Ttlv Serialization Tests")
class ValidationInformationTtlvTest
    extends AbstractTtlvSerializationTestSuite<ValidationInformation> {

  @Override
  public Class<ValidationInformation> type() {
    return ValidationInformation.class;
  }

  @Override
  public ValidationInformation createDefault() {
    return ValidationInformation.of(
        ValidationAuthorityType.Standard.UNSPECIFIED.inst(),
        ValidationVersionMajor.of(1),
        ValidationType.Standard.UNSPECIFIED.inst(),
        ValidationLevel.of(1));
  }

  @Override
  public ValidationInformation createVariant() {
    return ValidationInformation.of(
        ValidationAuthorityType.Standard.UNSPECIFIED.inst(),
        ValidationVersionMajor.of(1),
        ValidationType.Standard.UNSPECIFIED.inst(),
        ValidationLevel.of(2));
  }
}