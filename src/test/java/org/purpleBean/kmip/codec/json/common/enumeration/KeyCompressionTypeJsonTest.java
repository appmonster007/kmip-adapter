package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.KeyCompressionType;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("KeyCompressionType JSON Serialization")
class KeyCompressionTypeJsonTest extends AbstractJsonSerializationSuite<KeyCompressionType> {
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
