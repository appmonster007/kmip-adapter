package org.purpleBean.kmip.model.v2_1.structure.response.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("GetAttributesOpResponsePayload Domain Tests")
class GetAttributesOpResponsePayloadTest
    extends AbstractKmipStructureTestSuite<GetAttributesOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<GetAttributesOpResponsePayload> type() {
    return GetAttributesOpResponsePayload.class;
  }

  @Override
  protected GetAttributesOpResponsePayload createDefault() {
    return GetAttributesOpResponsePayload
        .builder()
        .uniqueIdentifier(UniqueIdentifier.of("test-uid"))
        .attributes(Attributes.of(List.of()))
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
        .filter(v -> v instanceof UniqueIdentifier)
        .count()).isEqualTo(1);
    assertThat(values
        .stream()
        .filter(v -> v instanceof Attributes)
        .count()).isEqualTo(1);
  }
}
