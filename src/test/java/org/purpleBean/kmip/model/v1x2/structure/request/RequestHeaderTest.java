package org.purpleBean.kmip.model.v1x2.structure.request;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.type.BatchCount;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMajor;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMinor;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("RequestHeader Domain Tests")
class RequestHeaderTest extends AbstractKmipStructureTestSuite<RequestHeader> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<RequestHeader> type() {
    return RequestHeader.class;
  }

  @Override
  protected RequestHeader createDefault() {
    return RequestHeader
        .builder()
        .protocolVersion(ProtocolVersion
            .builder()
            .protocolVersionMajor(ProtocolVersionMajor.of(1))
            .protocolVersionMinor(ProtocolVersionMinor.of(2))
            .build())
        .batchCount(BatchCount.of(1))
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
    assertThat(values.get(0)).isInstanceOf(ProtocolVersion.class);
    assertThat(values.get(1)).isInstanceOf(BatchCount.class);
  }
}