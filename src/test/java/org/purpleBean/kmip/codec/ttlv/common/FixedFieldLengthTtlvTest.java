package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.FixedFieldLength;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("FixedFieldLength TTLV Serialization Tests")
class FixedFieldLengthTtlvTest extends AbstractTtlvSerializationSuite<FixedFieldLength> {

    @Override
    protected Class<FixedFieldLength> type() {
        return FixedFieldLength.class;
    }

    @Override
    protected FixedFieldLength createDefault() {
        return FixedFieldLength.of(128);
    }

    @Override
    protected FixedFieldLength createVariant() {
        return FixedFieldLength.of(256);
    }
}