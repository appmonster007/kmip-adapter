package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.DataByteString;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

import java.nio.ByteBuffer;

@DisplayName("DataByteString TTLV Serialization Tests")
class DataByteStringTtlvTest extends AbstractTtlvSerializationSuite<DataByteString> {

    @Override
    protected Class<DataByteString> type() {
        return DataByteString.class;
    }

    @Override
    protected DataByteString createDefault() {
        return DataByteString.of(ByteBuffer.wrap("test data".getBytes()));
    }

    @Override
    protected DataByteString createVariant() {
        return DataByteString.of(ByteBuffer.wrap("variant data".getBytes()));
    }
}
