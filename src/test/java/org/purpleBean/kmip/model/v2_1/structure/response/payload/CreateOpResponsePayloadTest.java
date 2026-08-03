package org.purpleBean.kmip.model.v2_1.structure.response.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("CreateOpResponsePayload Domain Tests")
class CreateOpResponsePayloadTest extends AbstractKmipStructureTestSuite<CreateOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<CreateOpResponsePayload> type() {
    return CreateOpResponsePayload.class;
  }

  @Override
  protected CreateOpResponsePayload createDefault() {
    return CreateOpResponsePayload
        .builder()
        .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
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
        .filter(v -> v instanceof ObjectType)
        .count()).isEqualTo(1);
    assertThat(values
        .stream()
        .filter(v -> v instanceof UniqueIdentifier)
        .count()).isEqualTo(1);
  }
}
