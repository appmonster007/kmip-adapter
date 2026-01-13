package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.KeyPartIdentifier;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("KeyPartIdentifier TTLV Serialization Tests")
class KeyPartIdentifierTtlvTest extends AbstractTtlvSerializationTestSuite<KeyPartIdentifier> {

    @Override
    protected Class<KeyPartIdentifier> type() {
        return KeyPartIdentifier.class;
    }

    @Override
    protected KeyPartIdentifier createDefault() {
        return KeyPartIdentifier.builder().value(1).build();
    }

    @Override
    protected KeyPartIdentifier createVariant() {
        return KeyPartIdentifier.builder().value(2).build();
    }
}