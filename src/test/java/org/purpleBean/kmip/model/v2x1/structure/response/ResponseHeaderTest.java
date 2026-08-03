package org.purpleBean.kmip.model.v2x1.structure.response;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.type.BatchCount;
import org.purpleBean.kmip.model.core.type.TimeStamp;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("ResponseHeader Domain Tests")
class ResponseHeaderTest extends AbstractKmipStructureTestSuite<ResponseHeader> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<ResponseHeader> type() {
    return ResponseHeader.class;
  }

  @Override
  protected ResponseHeader createDefault() {
    return ResponseHeader
        .builder()
        .protocolVersion(ProtocolVersion.of(2, 1))
        .timeStamp(TimeStamp.of(OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)))
        .batchCount(BatchCount.of(1))
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    return 3;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    assertThat(values.size()).isGreaterThanOrEqualTo(3);
    assertThat(values.get(0)).isInstanceOf(ProtocolVersion.class);
    assertThat(values.get(1)).isInstanceOf(TimeStamp.class);
    assertThat(values.get(2)).isInstanceOf(BatchCount.class);
  }
}
