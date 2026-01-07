package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

import java.nio.ByteBuffer;

@DisplayName("DataByteString Domain Tests")
class DataByteStringTest extends AbstractKmipDataTypeSuite<DataByteString> {

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
