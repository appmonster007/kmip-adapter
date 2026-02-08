package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.KeyCompressionType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("KeyCompressionType TTLV Serialization")
class KeyCompressionTypeTtlvTest extends AbstractTtlvSerializationTestSuite<KeyCompressionType> {
    @Override
    public Class<KeyCompressionType> type() {
        return KeyCompressionType.class;
    }

    @Override
    public KeyCompressionType createDefault() {
        return KeyCompressionType.Standard.EC_PUBLIC_KEY_TYPE_UNCOMPRESSED.inst();
    }

    @Override
    public KeyCompressionType createVariant() {
        return KeyCompressionType.Standard.EC_PUBLIC_KEY_TYPE_X9_62_COMPRESSED_PRIME.inst();
    }
}
