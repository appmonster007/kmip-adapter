package org.purplebean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.LogMessage;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("LogMessage Xml Serialization Tests")
class LogMessageXmlTest extends AbstractXmlSerializationTestSuite<LogMessage> {

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