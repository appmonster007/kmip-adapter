package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.KeyValuePresent;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("KeyValuePresent Xml Serialization Tests")
class KeyValuePresentXmlTest extends AbstractXmlSerializationTestSuite<KeyValuePresent> {

  @Override
  public Class<KeyValuePresent> type() {
    return KeyValuePresent.class;
  }

  @Override
  public KeyValuePresent createDefault() {
    return KeyValuePresent.of(Boolean.FALSE);
  }

  @Override
  public KeyValuePresent createVariant() {
    return KeyValuePresent.of(Boolean.TRUE);
  }
}