package org.purpleBean.kmip.model.v1x2.structure.request.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.DataLength;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("RngRetrieveOpRequestPayload Domain Tests")
class RngRetrieveOpRequestPayloadTest
    extends AbstractKmipStructureTestSuite<RngRetrieveOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<RngRetrieveOpRequestPayload> type() {
    return RngRetrieveOpRequestPayload.class;
  }

  @Override
  protected RngRetrieveOpRequestPayload createDefault() {
    return RngRetrieveOpRequestPayload
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
