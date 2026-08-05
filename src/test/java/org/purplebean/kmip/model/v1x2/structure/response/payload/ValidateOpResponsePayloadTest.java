package org.purplebean.kmip.model.v1x2.structure.response.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.ValidityIndicator;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("ValidateOpResponsePayload Domain Tests")
class ValidateOpResponsePayloadTest
    extends AbstractKmipStructureTestSuite<ValidateOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<ValidateOpResponsePayload> type() {
    return ValidateOpResponsePayload.class;
  }

  @Override
  protected ValidateOpResponsePayload createDefault() {
    return ValidateOpResponsePayload
        .builder()
        .validityIndicator(ValidityIndicator.of(ValidityIndicator.Standard.VALID))
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    return 1;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    assertThat(values).hasSize(1);
    assertThat(values.get(0)).isInstanceOf(ValidityIndicator.class);
  }
}
