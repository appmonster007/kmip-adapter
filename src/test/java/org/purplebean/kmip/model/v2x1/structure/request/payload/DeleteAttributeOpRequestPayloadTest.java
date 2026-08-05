package org.purplebean.kmip.model.v2x1.structure.request.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.AttributeReference;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("DeleteAttributeOpRequestPayload Domain Tests")
class DeleteAttributeOpRequestPayloadTest
    extends AbstractKmipStructureTestSuite<DeleteAttributeOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<DeleteAttributeOpRequestPayload> type() {
    return DeleteAttributeOpRequestPayload.class;
  }

  @Override
  protected DeleteAttributeOpRequestPayload createDefault() {
    return DeleteAttributeOpRequestPayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .attributeReference(AttributeReference
            .builder()
            .attributeName(AttributeName.of("Contact Information"))
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
    assertThat(values.get(1)).isInstanceOf(AttributeReference.class);
  }
}
