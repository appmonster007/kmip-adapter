package org.purplebean.kmip.codec.json.model.core.type;

import java.nio.ByteBuffer;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("DataByteString JSON Serialization Tests")
class DataByteStringJsonTest extends AbstractJsonSerializationTestSuite<DataByteString> {

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
