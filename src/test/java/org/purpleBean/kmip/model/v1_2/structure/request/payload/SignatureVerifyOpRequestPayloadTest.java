package org.purpleBean.kmip.model.v1_2.structure.request.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.SignatureData;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("SignatureVerifyOpRequestPayload Domain Tests")
class SignatureVerifyOpRequestPayloadTest
    extends AbstractKmipStructureTestSuite<SignatureVerifyOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<SignatureVerifyOpRequestPayload> type() {
    return SignatureVerifyOpRequestPayload.class;
  }

  @Override
  protected SignatureVerifyOpRequestPayload createDefault() {
    return SignatureVerifyOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .data(DataByteString.of(new byte[] {1, 2, 3}))
        .signatureData(SignatureData.of(new byte[] {4, 5, 6}))
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
    assertThat(values).hasSize(3);
    assertThat(values.get(0)).isInstanceOf(UniqueIdentifier.class);
    assertThat(values.get(1)).isInstanceOf(DataByteString.class);
    assertThat(values.get(2)).isInstanceOf(SignatureData.class);
  }
}
