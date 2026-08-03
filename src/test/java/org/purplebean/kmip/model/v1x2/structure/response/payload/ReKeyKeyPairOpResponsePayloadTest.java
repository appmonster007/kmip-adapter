package org.purplebean.kmip.model.v1x2.structure.response.payload;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purplebean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purplebean.kmip.model.core.type.PrivateKeyUniqueIdentifier;
import org.purplebean.kmip.model.core.type.PublicKeyUniqueIdentifier;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("ReKeyKeyPairOpResponsePayload Domain Tests")
class ReKeyKeyPairOpResponsePayloadTest
    extends AbstractKmipStructureTestSuite<ReKeyKeyPairOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<ReKeyKeyPairOpResponsePayload> type() {
    return ReKeyKeyPairOpResponsePayload.class;
  }

  @Override
  protected ReKeyKeyPairOpResponsePayload createDefault() {
    return ReKeyKeyPairOpResponsePayload
        .builder()
        .privateKeyUniqueIdentifier(PrivateKeyUniqueIdentifier
            .builder()
            .value("private-uid")
            .build())
        .publicKeyUniqueIdentifier(PublicKeyUniqueIdentifier
            .builder()
            .value("public-uid")
            .build())
        .privateKeyTemplateAttribute(PrivateKeyTemplateAttribute
            .builder()
            .build())
        .publicKeyTemplateAttribute(PublicKeyTemplateAttribute
            .builder()
            .build())
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
    assertThat(values).hasSize(4);
    assertThat(values.get(0)).isInstanceOf(PrivateKeyUniqueIdentifier.class);
    assertThat(values.get(1)).isInstanceOf(PublicKeyUniqueIdentifier.class);
    assertThat(values.get(2)).isInstanceOf(PrivateKeyTemplateAttribute.class);
    assertThat(values.get(3)).isInstanceOf(PublicKeyTemplateAttribute.class);
  }
}