package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.SaltLength;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("SaltLength XML Serialization")
class SaltLengthXmlTest extends AbstractXmlSerializationTestSuite<SaltLength> {
  @Override
  public Class<SaltLength> type() {
    return SaltLength.class;
  }

  @Override
  public SaltLength createDefault() {
    return SaltLength.of(123);
  }

  @Override
  public SaltLength createVariant() {
    return SaltLength.of(456);
  }
}
