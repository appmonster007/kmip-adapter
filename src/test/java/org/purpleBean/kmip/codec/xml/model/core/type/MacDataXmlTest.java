package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.MacData;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.nio.ByteBuffer;

@DisplayName("MacData XML Serialization Tests")
class MacDataXmlTest extends AbstractXmlSerializationTestSuite<MacData> {

    @Override
    public Class<MacData> type() {
        return MacData.class;
    }

    @Override
    public MacData createDefault() {
        return MacData.of(ByteBuffer.wrap("test mac data".getBytes()));
    }

    @Override
    public MacData createVariant() {
        return MacData.of(ByteBuffer.wrap("variant mac data".getBytes()));
    }
}
