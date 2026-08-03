package org.purplebean.kmip.model.v2x1.structure.request.payload;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.enumeration.SplitKeyMethod;
import org.purplebean.kmip.model.core.type.SplitKeyParts;
import org.purplebean.kmip.model.core.type.SplitKeyThreshold;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("CreateSplitKeyOpRequestPayload Domain Tests")
class CreateSplitKeyOpRequestPayloadTest
    extends AbstractKmipStructureTestSuite<CreateSplitKeyOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<CreateSplitKeyOpRequestPayload> type() {
    return CreateSplitKeyOpRequestPayload.class;
  }

  @Override
  protected CreateSplitKeyOpRequestPayload createDefault() {
    return CreateSplitKeyOpRequestPayload
        .builder()
        .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("source-key-id")
            .build())
        .splitKeyParts(SplitKeyParts.of(3))
        .splitKeyThreshold(SplitKeyThreshold.of(2))
        .splitKeyMethod(SplitKeyMethod.of(SplitKeyMethod.Standard.XOR))
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    return 5;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    assertThat(values).hasSize(5);
    assertThat(values.get(0)).isInstanceOf(ObjectType.class);
    assertThat(values.get(1)).isInstanceOf(UniqueIdentifier.class);
    assertThat(values.get(2)).isInstanceOf(SplitKeyParts.class);
    assertThat(values.get(3)).isInstanceOf(SplitKeyThreshold.class);
    assertThat(values.get(4)).isInstanceOf(SplitKeyMethod.class);
  }
}