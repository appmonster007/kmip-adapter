package org.purplebean.kmip.model.v1x2.structure.request.payload;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("RngSeedOpRequestPayload Domain Tests")
class RngSeedOpRequestPayloadTest extends AbstractKmipStructureTestSuite<RngSeedOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<RngSeedOpRequestPayload> type() {
    return RngSeedOpRequestPayload.class;
  }

  @Override
  protected RngSeedOpRequestPayload createDefault() {
    return RngSeedOpRequestPayload
        .builder()
        .data(DataByteString.of(new byte[] {1, 2, 3}))
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
    assertThat(values.get(0)).isInstanceOf(DataByteString.class);
  }
}
