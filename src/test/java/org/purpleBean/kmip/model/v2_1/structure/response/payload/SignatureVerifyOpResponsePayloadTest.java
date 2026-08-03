package org.purpleBean.kmip.model.v2_1.structure.response.payload;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("SignatureVerifyOpResponsePayload Domain Tests")
class SignatureVerifyOpResponsePayloadTest
    extends AbstractKmipStructureTestSuite<SignatureVerifyOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<SignatureVerifyOpResponsePayload> type() {
    return SignatureVerifyOpResponsePayload.class;
  }

  @Override
  protected SignatureVerifyOpResponsePayload createDefault() {
    return SignatureVerifyOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
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
    // TODO: Validate the components of the structure
    // assertThat(values).hasSize(0);
  }
}