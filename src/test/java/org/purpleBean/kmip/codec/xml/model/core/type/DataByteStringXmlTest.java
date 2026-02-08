package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.nio.ByteBuffer;

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
