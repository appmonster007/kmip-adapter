package org.purpleBean.kmip.model.core.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("DataByteString Domain Tests")
class DataByteStringTest extends AbstractKmipDataTypeTestSuite<DataByteString> {

  @Override
  protected Class<DataByteString> type() {
    return DataByteString.class;
  }

  @Override
  protected DataByteString createDefault() {
    return DataByteString.of(ByteBuffer.wrap("test data".getBytes()));
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BYTE_STRING;
  }
}
