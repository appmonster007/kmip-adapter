package org.purpleBean.kmip.model.v2_1.structure.request.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("GetConstraintsOpRequestPayload Domain Tests")
class GetConstraintsOpRequestPayloadTest
    extends AbstractKmipStructureTestSuite<GetConstraintsOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<GetConstraintsOpRequestPayload> type() {
    return GetConstraintsOpRequestPayload.class;
  }

  @Override
  protected GetConstraintsOpRequestPayload createDefault() {
    return GetConstraintsOpRequestPayload
        .builder()
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  public int expectedMinComponentCount() {
    return 0;
  }

  @Override
  public void validateComponents(List<KmipDataType> values) {
    assertThat(values).hasSizeLessThanOrEqualTo(1);
  }
}