package org.purplebean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.KeyValueLocationType;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("KeyValueLocationType XML Serialization")
class KeyValueLocationTypeXmlTest extends AbstractXmlSerializationTestSuite<KeyValueLocationType> {
  @Override
  public Class<KeyValueLocationType> type() {
    return KeyValueLocationType.class;
  }

  @Override
  public KeyValueLocationType createDefault() {
    return KeyValueLocationType.Standard.UNINTERPRETED_TEXT_STRING.inst();
  }

  @Override
  public KeyValueLocationType createVariant() {
    return KeyValueLocationType.Standard.URI.inst();
  }
}
