package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.QString;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("QString XML Serialization Tests")
class QStringXmlTest extends AbstractXmlSerializationTestSuite<QString> {

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