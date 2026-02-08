package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.nio.ByteBuffer;

@DisplayName("DataByteString TTLV Serialization Tests")
class DataByteStringTtlvTest extends AbstractTtlvSerializationTestSuite<DataByteString> {

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
