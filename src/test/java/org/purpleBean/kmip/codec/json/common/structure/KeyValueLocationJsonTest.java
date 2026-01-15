package org.purpleBean.kmip.codec.json.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.KeyValueLocationValue;
import org.purpleBean.kmip.common.enumeration.KeyValueLocationType;
import org.purpleBean.kmip.common.structure.KeyValueLocation;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("KeyValueLocation Json Serialization Tests")
class KeyValueLocationJsonTest extends AbstractJsonSerializationTestSuite<KeyValueLocation> {

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