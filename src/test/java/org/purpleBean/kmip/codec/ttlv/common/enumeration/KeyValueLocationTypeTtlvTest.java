package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.KeyValueLocationType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("KeyValueLocationType TTLV Serialization")
class KeyValueLocationTypeTtlvTest extends AbstractTtlvSerializationSuite<KeyValueLocationType> {
    @Override
    protected Class<KeyValueLocationType> type() {
        return KeyValueLocationType.class;
    }

    @Override
    protected KeyValueLocationType createDefault() {
        return new KeyValueLocationType(KeyValueLocationType.Standard.UNINTERPRETED_TEXT_STRING);
    }

    @Override
    protected KeyValueLocationType createVariant() {
        return new KeyValueLocationType(KeyValueLocationType.Standard.URI);
    }
}
