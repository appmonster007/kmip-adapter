package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.nio.ByteBuffer;

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
