package org.purpleBean.kmip.codec.ttlv.model.v2x1.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.enumeration.DataEnumeration;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DataEnumeration Ttlv Serialization Tests")
class DataEnumerationTtlvTest extends AbstractTtlvSerializationTestSuite<DataEnumeration> {

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