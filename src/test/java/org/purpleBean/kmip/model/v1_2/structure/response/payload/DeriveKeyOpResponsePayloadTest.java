package org.purpleBean.kmip.model.v1_2.structure.response.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("DeriveKeyOpResponsePayload Domain Tests")
class DeriveKeyOpResponsePayloadTest
    extends AbstractKmipStructureTestSuite<DeriveKeyOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<DeriveKeyOpResponsePayload> type() {
    return DeriveKeyOpResponsePayload.class;
  }

  @Override
  protected DeriveKeyOpResponsePayload createDefault() {
    return DeriveKeyOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("uid")
            .build())
        .templateAttribute(TemplateAttribute
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
    return 1;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    assertThat(values).hasSize(2);
    assertThat(values.get(0)).isInstanceOf(UniqueIdentifier.class);
    assertThat(values.get(1)).isInstanceOf(TemplateAttribute.class);
  }
}
