package org.purpleBean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

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