package org.purplebean.kmip.model.v3x0.structure.response;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.core.type.TimeStamp;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("ResponseHeader Domain Tests")
class ResponseHeaderTest extends AbstractKmipStructureTestSuite<ResponseHeader> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V3_0;
  }

  @Override
  protected Class<ResponseHeader> type() {
    return ResponseHeader.class;
  }

  @Override
  protected ResponseHeader createDefault() {
    return ResponseHeader
        .builder()
        .protocolVersion(ProtocolVersion.of(3, 0))
        .timeStamp(TimeStamp.of(OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC)))
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
    assertThat(values.get(1)).isInstanceOf(TimeStamp.class);
  }
}