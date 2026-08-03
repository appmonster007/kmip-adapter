package org.purpleBean.kmip.model.v2x1.structure.response.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.PrivateKeyUniqueIdentifier;
import org.purpleBean.kmip.model.core.type.PublicKeyUniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("CreateKeyPairOpResponsePayload Domain Tests")
class CreateKeyPairOpResponsePayloadTest
    extends AbstractKmipStructureTestSuite<CreateKeyPairOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<CreateKeyPairOpResponsePayload> type() {
    return CreateKeyPairOpResponsePayload.class;
  }

  @Override
  protected CreateKeyPairOpResponsePayload createDefault() {
    return CreateKeyPairOpResponsePayload
        .builder()
        .privateKeyUniqueIdentifier(PrivateKeyUniqueIdentifier.of("test-priv-uid"))
        .publicKeyUniqueIdentifier(PublicKeyUniqueIdentifier.of("test-pub-uid"))
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    return 2;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    assertThat(values
        .stream()
        .filter(v -> v instanceof PrivateKeyUniqueIdentifier)
        .count()).isEqualTo(1);
    assertThat(values
        .stream()
        .filter(v -> v instanceof PublicKeyUniqueIdentifier)
        .count()).isEqualTo(1);
  }
}
