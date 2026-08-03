package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.Qlength;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Qlength XML Serialization Tests")
class QlengthXmlTest extends AbstractXmlSerializationTestSuite<Qlength> {

  @Override
  public Class<Qlength> type() {
    return Qlength.class;
  }

  @Override
  public Qlength createDefault() {
    return Qlength
        .builder()
        .value(128)
        .build();
  }

  @Override
  public Qlength createVariant() {
    return Qlength
        .builder()
        .value(256)
        .build();
  }
}