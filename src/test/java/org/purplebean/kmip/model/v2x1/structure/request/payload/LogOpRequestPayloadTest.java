package org.purplebean.kmip.model.v2x1.structure.request.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.v2x1.type.LogMessage;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("LogOpRequestPayload Domain Tests")
class LogOpRequestPayloadTest extends AbstractKmipStructureTestSuite<LogOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<LogOpRequestPayload> type() {
    return LogOpRequestPayload.class;
  }

  @Override
  protected LogOpRequestPayload createDefault() {
    return LogOpRequestPayload
        .builder()
        .logMessage(LogMessage
            .builder()
            .value("test-log-message")
            .build())
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  public int expectedMinComponentCount() {
    return 1;
  }

  @Override
  public void validateComponents(List<KmipDataType> values) {
    assertThat(values).hasSize(1);
    assertThat(values.get(0)).isInstanceOf(LogMessage.class);
  }
}