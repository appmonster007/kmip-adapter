package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.MACSignature;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

import java.nio.ByteBuffer;

@DisplayName("MACSignature TTLV Serialization Tests")
class MACSignatureTtlvTest extends AbstractTtlvSerializationSuite<MACSignature> {

    @Override
    protected Class<MACSignature> type() {
        return MACSignature.class;
    }

    @Override
    protected MACSignature createDefault() {
        return MACSignature.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected MACSignature createVariant() {
        return MACSignature.of(new byte[]{0x04, 0x05, 0x06});
    }
}