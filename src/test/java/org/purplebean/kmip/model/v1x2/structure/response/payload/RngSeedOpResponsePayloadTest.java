package org.purplebean.kmip.model.v1x2.structure.response.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.DataLength;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("RngSeedOpResponsePayload Domain Tests")
class RngSeedOpResponsePayloadTest
    extends AbstractKmipStructureTestSuite<RngSeedOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<RngSeedOpResponsePayload> type() {
    return RngSeedOpResponsePayload.class;
  }

  @Override
  protected RngSeedOpResponsePayload createDefault() {
    return RngSeedOpResponsePayload
        .builder()
        .dataLength(DataLength.of(16))
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
    assertThat(values).hasSize(1);
    assertThat(values.get(0)).isInstanceOf(DataLength.class);
  }
}
