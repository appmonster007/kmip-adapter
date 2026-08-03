package org.purplebean.kmip.model.v2x1.structure.response;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.type.BatchCount;
import org.purplebean.kmip.model.core.type.TimeStamp;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("ResponseMessage Domain Tests")
class ResponseMessageTest extends AbstractKmipStructureTestSuite<ResponseMessage> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<ResponseMessage> type() {
    return ResponseMessage.class;
  }

  @Override
  protected ResponseMessage createDefault() {
    return ResponseMessage
        .builder()
        .responseHeader(ResponseHeader
            .builder()
            .protocolVersion(ProtocolVersion.of(2, 1))
            .timeStamp(TimeStamp.of(OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)))
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
    assertThat(values.get(0)).isInstanceOf(ResponseHeader.class);
  }
}
