package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("QString Domain Tests")
class QStringTest extends AbstractKmipDataTypeTestSuite<QString> {

  @Override
  protected Class<QString> type() {
    return QString.class;
  }

  @Override
  protected QString createDefault() {
    return QString.of("test-qstring".getBytes());
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BYTE_STRING;
  }
}