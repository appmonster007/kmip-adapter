package org.purplebean.kmip.model.v2x1.structure;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.ValidationAuthorityType;
import org.purplebean.kmip.model.core.enumeration.ValidationType;
import org.purplebean.kmip.model.v2x1.type.ValidationLevel;
import org.purplebean.kmip.model.v2x1.type.ValidationVersionMajor;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("ValidationInformation Domain Tests")
class ValidationInformationTest extends AbstractKmipStructureTestSuite<ValidationInformation> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<ValidationInformation> type() {
    return ValidationInformation.class;
  }

  @Override
  protected ValidationInformation createDefault() {
    return ValidationInformation.of(
        ValidationAuthorityType.Standard.UNSPECIFIED.inst(),
        ValidationVersionMajor.of(1),
        ValidationType.Standard.UNSPECIFIED.inst(),
        ValidationLevel.of(1));
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    // TODO: Set the expected minimum number of components
    return 0;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    // TODO: Validate the components of the structure
    // assertThat(values).hasSize(0);
  }
}