package org.purplebean.kmip.codec.xml.model.v3x0.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v3x0.type.NistSecurityCategory;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("NistSecurityCategory Xml Serialization Tests")
class NistSecurityCategoryXmlTest extends AbstractXmlSerializationTestSuite<NistSecurityCategory> {

  @Override
  public Class<NistSecurityCategory> type() {
    return NistSecurityCategory.class;
  }

  @Override
  public NistSecurityCategory createDefault() {
    return NistSecurityCategory.of(123);
  }

  @Override
  public NistSecurityCategory createVariant() {
    return NistSecurityCategory.of(456);
  }
}