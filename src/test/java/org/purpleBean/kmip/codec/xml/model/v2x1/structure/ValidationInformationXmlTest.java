package org.purpleBean.kmip.codec.xml.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ValidationAuthorityType;
import org.purpleBean.kmip.model.core.enumeration.ValidationType;
import org.purpleBean.kmip.model.v2x1.structure.ValidationInformation;
import org.purpleBean.kmip.model.v2x1.type.ValidationLevel;
import org.purpleBean.kmip.model.v2x1.type.ValidationVersionMajor;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ValidationInformation Xml Serialization Tests")
class ValidationInformationXmlTest
    extends AbstractXmlSerializationTestSuite<ValidationInformation> {

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