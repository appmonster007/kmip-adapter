package org.purpleBean.kmip.model.v2_1.structure.request;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.type.BatchCount;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("RequestMessage Domain Tests")
class RequestMessageTest extends AbstractKmipStructureTestSuite<RequestMessage> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<RequestMessage> type() {
    return RequestMessage.class;
  }

  @Override
  protected RequestMessage createDefault() {
    return RequestMessage
        .builder()
        .requestHeader(RequestHeader
            .builder()
            .protocolVersion(ProtocolVersion.of(2, 1))
            .batchCount(BatchCount.of(0))
            .build())
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
    assertThat(values.size()).isGreaterThanOrEqualTo(1);
    assertThat(values.get(0)).isInstanceOf(RequestHeader.class);
  }
}
