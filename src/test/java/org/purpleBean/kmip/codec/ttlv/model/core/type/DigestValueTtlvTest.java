package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.DigestValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DigestValue TTLV Serialization Tests")
class DigestValueTtlvTest extends AbstractTtlvSerializationTestSuite<DigestValue> {

    @Override
    public Class<DigestValue> type() {
        return DigestValue.class;
    }

    @Override
    public DigestValue createDefault() {
        return DigestValue.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    public DigestValue createVariant() {
        return DigestValue.of(new byte[]{0x04, 0x05, 0x06});
    }
}