package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.QString;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("QString JSON Serialization Tests")
class QStringJsonTest extends AbstractJsonSerializationTestSuite<QString> {

  @Override
  public Class<QString> type() {
    return QString.class;
  }

  @Override
  public QString createDefault() {
    return QString.of("test-qstring".getBytes());
  }

  @Override
  public QString createVariant() {
    return QString.of("another-qstring".getBytes());
  }
}