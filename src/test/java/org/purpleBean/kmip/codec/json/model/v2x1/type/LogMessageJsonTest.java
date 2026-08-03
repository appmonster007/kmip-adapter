package org.purpleBean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.LogMessage;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("LogMessage Json Serialization Tests")
class LogMessageJsonTest extends AbstractJsonSerializationTestSuite<LogMessage> {

  @Override
  public Class<LogMessage> type() {
    return LogMessage.class;
  }

  @Override
  public LogMessage createDefault() {
    return LogMessage.of("default-string");
  }

  @Override
  public LogMessage createVariant() {
    return LogMessage.of("variant-string");
  }
}