package org.purpleBean.kmip.model.v1x2.structure.response.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("JoinSplitKeyOpResponsePayload Domain Tests")
class JoinSplitKeyOpResponsePayloadTest
    extends AbstractKmipStructureTestSuite<JoinSplitKeyOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<JoinSplitKeyOpResponsePayload> type() {
    return JoinSplitKeyOpResponsePayload.class;
  }

  @Override
  protected JoinSplitKeyOpResponsePayload createDefault() {
    return JoinSplitKeyOpResponsePayload
        .builder()
        .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
        .uniqueIdentifier(UniqueIdentifier.of("123e4567-e89b-12d3-a456-426614174000"))
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
    assertThat(values).hasSize(2);
    assertThat(values.get(0)).isInstanceOf(ObjectType.class);
    assertThat(values.get(1)).isInstanceOf(UniqueIdentifier.class);
  }
}
