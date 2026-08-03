package org.purpleBean.kmip.codec.xml.model.v2_1.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.enumeration.DataEnumeration;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("DataEnumeration Xml Serialization Tests")
class DataEnumerationXmlTest extends AbstractXmlSerializationTestSuite<DataEnumeration> {

  @Override
  public Class<DataEnumeration> type() {
    return DataEnumeration.class;
  }

  @Override
  public DataEnumeration createDefault() {
    return DataEnumeration.Standard.values()[0].inst();
  }

  @Override
  public DataEnumeration createVariant() {
    return DataEnumeration.Standard.values()[1].inst();
  }
}