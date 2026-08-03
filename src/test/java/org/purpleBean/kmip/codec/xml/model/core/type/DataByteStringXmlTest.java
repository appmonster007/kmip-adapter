package org.purplebean.kmip.codec.xml.model.core.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("DataByteString XML Serialization Tests")
class DataByteStringXmlTest extends AbstractXmlSerializationTestSuite<DataByteString> {

  @Override
  public Class<DataByteString> type() {
    return DataByteString.class;
  }

  @Override
  public DataByteString createDefault() {
    return DataByteString.of(ByteBuffer.wrap("test data".getBytes()));
  }

  @Override
  public DataByteString createVariant() {
    return DataByteString.of(ByteBuffer.wrap("variant data".getBytes()));
  }
}
