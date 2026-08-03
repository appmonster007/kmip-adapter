package org.purpleBean.kmip.model.v1_2.structure.request.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMajor;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMinor;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("DiscoverVersionsOpRequestPayload Domain Tests")
class DiscoverVersionsOpRequestPayloadTest
    extends AbstractKmipStructureTestSuite<DiscoverVersionsOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<DiscoverVersionsOpRequestPayload> type() {
    return DiscoverVersionsOpRequestPayload.class;
  }

  @Override
  protected DiscoverVersionsOpRequestPayload createDefault() {
    return DiscoverVersionsOpRequestPayload
        .builder()
        .protocolVersion(ProtocolVersion.of(ProtocolVersionMajor.of(1), ProtocolVersionMinor.of(2)))
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    return 0;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    assertThat(values).hasSize(1);
    assertThat(values.get(0)).isInstanceOf(ProtocolVersion.class);
  }
}
