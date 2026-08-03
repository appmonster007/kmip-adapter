package org.purplebean.kmip.codec.xml.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.type.Ephemeral;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Ephemeral Xml Serialization Tests")
class EphemeralXmlTest extends AbstractXmlSerializationTestSuite<Ephemeral> {

  @Override
  public Class<Ephemeral> type() {
    return Ephemeral.class;
  }

  @Override
  public Ephemeral createDefault() {
    return Ephemeral.of(true);
  }

  @Override
  public Ephemeral createVariant() {
    return Ephemeral.of(false);
  }
}