package org.purplebean.kmip.model.v1x2.structure.response;

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
import org.purplebean.kmip.model.core.type.ProtocolVersionMajor;
import org.purplebean.kmip.model.core.type.ProtocolVersionMinor;
import org.purplebean.kmip.model.core.type.TimeStamp;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("ResponseHeader Domain Tests")
class ResponseHeaderTest extends AbstractKmipStructureTestSuite<ResponseHeader> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  protected Class<ResponseHeader> type() {
    return ResponseHeader.class;
  }

  @Override
  protected ResponseHeader createDefault() {
    return ResponseHeader
        .builder()
        .protocolVersion(ProtocolVersion.of(ProtocolVersionMajor.of(1), ProtocolVersionMinor.of(2)))
        .timeStamp(TimeStamp.of(OffsetDateTime.now(ZoneOffset.UTC)))
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
    assertThat(values).hasSize(3);
    assertThat(values.get(0)).isInstanceOf(ProtocolVersion.class);
    assertThat(values.get(1)).isInstanceOf(TimeStamp.class);
    assertThat(values.get(2)).isInstanceOf(BatchCount.class);
  }
}
