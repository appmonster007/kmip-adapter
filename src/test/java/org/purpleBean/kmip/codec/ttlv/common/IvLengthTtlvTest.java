package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.IvLength;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("IvLength TTLV Serialization Tests")
class IvLengthTtlvTest extends AbstractTtlvSerializationSuite<IvLength> {

    @Override
    protected Class<IvLength> type() {
        return IvLength.class;
    }

    @Override
    protected IvLength createDefault() {
        return IvLength.of(128);
    }

    @Override
    protected IvLength createVariant() {
        return IvLength.of(256);
    }
}