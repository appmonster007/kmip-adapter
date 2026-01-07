package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.MacData;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

import java.nio.ByteBuffer;

@DisplayName("MacData TTLV Serialization Tests")
class MacDataTtlvTest extends AbstractTtlvSerializationSuite<MacData> {

    @Override
    protected Class<MacData> type() {
        return MacData.class;
    }

    @Override
    protected MacData createDefault() {
        return MacData.of(ByteBuffer.wrap("test mac data".getBytes()));
    }

    @Override
    protected MacData createVariant() {
        return MacData.of(ByteBuffer.wrap("variant mac data".getBytes()));
    }
}
