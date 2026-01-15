package org.purpleBean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.KeyValueLocationType;
import org.purpleBean.kmip.model.core.structure.KeyValueLocation;
import org.purpleBean.kmip.model.core.type.KeyValueLocationValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("KeyValueLocation Ttlv Serialization Tests")
class KeyValueLocationTtlvTest extends AbstractTtlvSerializationTestSuite<KeyValueLocation> {

    @Override
    protected Class<KeyValueLocation> type() {
        return KeyValueLocation.class;
    }

    @Override
    protected KeyValueLocation createDefault() {
        return KeyValueLocation.builder()
                .keyValueLocationType(KeyValueLocationType.Standard.UNINTERPRETED_TEXT_STRING.inst())
                .keyValueLocationValue(KeyValueLocationValue.builder().value("test").build())
                .build();
    }
}