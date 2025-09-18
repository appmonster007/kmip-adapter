package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.KeyCompressionType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("KeyCompressionType TTLV Serialization")
class KeyCompressionTypeTtlvTest extends AbstractTtlvSerializationSuite<KeyCompressionType> {
    @Override
    protected Class<KeyCompressionType> type() {
        return KeyCompressionType.class;
    }

    @Override
    protected KeyCompressionType createDefault() {
        return new KeyCompressionType(KeyCompressionType.Standard.PLACEHOLDER_1);
    }

    @Override
    protected KeyCompressionType createVariant() {
        return new KeyCompressionType(KeyCompressionType.Standard.PLACEHOLDER_2);
    }
}
