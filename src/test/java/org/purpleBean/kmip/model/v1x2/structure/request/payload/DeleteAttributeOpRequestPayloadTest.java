package org.purpleBean.kmip.model.v1x2.structure.request.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.AttributeIndex;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("DeleteAttributeOpRequestPayload Domain Tests")
class DeleteAttributeOpRequestPayloadTest
    extends AbstractKmipStructureTestSuite<DeleteAttributeOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<DeleteAttributeOpRequestPayload> type() {
    return DeleteAttributeOpRequestPayload.class;
  }

  @Override
  protected DeleteAttributeOpRequestPayload createDefault() {
    return DeleteAttributeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
        .attributeName(AttributeName.of("test-attribute"))
        .attributeIndex(AttributeIndex.of(1))
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
    assertThat(values.get(1)).isInstanceOf(AttributeName.class);
    assertThat(values.get(2)).isInstanceOf(AttributeIndex.class);
  }
}
