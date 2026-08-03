package org.purplebean.kmip.codec.json.model.v2x1.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.enumeration.DataEnumeration;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("DataEnumeration Json Serialization Tests")
class DataEnumerationJsonTest extends AbstractJsonSerializationTestSuite<DataEnumeration> {

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