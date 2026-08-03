package org.purpleBean.kmip.model.v1_2.structure.request.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.CommonTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("CreateKeyPairOpRequestPayload Domain Tests")
class CreateKeyPairOpRequestPayloadTest
    extends AbstractKmipStructureTestSuite<CreateKeyPairOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<CreateKeyPairOpRequestPayload> type() {
    return CreateKeyPairOpRequestPayload.class;
  }

  @Override
  protected CreateKeyPairOpRequestPayload createDefault() {
    return CreateKeyPairOpRequestPayload
        .builder()
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
    assertThat(values).hasSize(3);
    assertThat(values.get(0)).isInstanceOf(CommonTemplateAttribute.class);
    assertThat(values.get(1)).isInstanceOf(PrivateKeyTemplateAttribute.class);
    assertThat(values.get(2)).isInstanceOf(PublicKeyTemplateAttribute.class);
  }
}