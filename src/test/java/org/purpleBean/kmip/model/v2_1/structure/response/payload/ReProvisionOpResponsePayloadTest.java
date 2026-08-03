package org.purpleBean.kmip.model.v2_1.structure.response.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("ReProvisionOpResponsePayload Domain Tests")
class ReProvisionOpResponsePayloadTest
    extends AbstractKmipStructureTestSuite<ReProvisionOpResponsePayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<ReProvisionOpResponsePayload> type() {
    return ReProvisionOpResponsePayload.class;
  }

  @Override
  protected ReProvisionOpResponsePayload createDefault() {
    return ReProvisionOpResponsePayload
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
    assertThat(values).isEmpty();
  }
}