package org.purplebean.kmip.model.v1x2.structure.request.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.structure.CommonTemplateAttribute;
import org.purplebean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purplebean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purplebean.kmip.model.core.type.Offset;
import org.purplebean.kmip.model.core.type.PrivateKeyUniqueIdentifier;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("ReKeyKeyPairOpRequestPayload Domain Tests")
class ReKeyKeyPairOpRequestPayloadTest
    extends AbstractKmipStructureTestSuite<ReKeyKeyPairOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<ReKeyKeyPairOpRequestPayload> type() {
    return ReKeyKeyPairOpRequestPayload.class;
  }

  @Override
  protected ReKeyKeyPairOpRequestPayload createDefault() {
    return ReKeyKeyPairOpRequestPayload
        .builder()
        .privateKeyUniqueIdentifier(PrivateKeyUniqueIdentifier
            .builder()
            .value("private-uid")
            .build())
        .offset(Offset
            .builder()
            .value(100)
            .build())
        .commonTemplateAttribute(CommonTemplateAttribute
            .builder()
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
    return 0;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    assertThat(values).hasSize(5);
    assertThat(values.get(0)).isInstanceOf(PrivateKeyUniqueIdentifier.class);
    assertThat(values.get(1)).isInstanceOf(Offset.class);
    assertThat(values.get(2)).isInstanceOf(CommonTemplateAttribute.class);
    assertThat(values.get(3)).isInstanceOf(PrivateKeyTemplateAttribute.class);
    assertThat(values.get(4)).isInstanceOf(PublicKeyTemplateAttribute.class);
  }
}