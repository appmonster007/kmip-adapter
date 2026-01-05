package org.purpleBean.kmip.codec.xml.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.KeyValueLocationValue;
import org.purpleBean.kmip.common.enumeration.KeyValueLocationType;
import org.purpleBean.kmip.common.structure.KeyValueLocation;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("KeyValueLocation Xml Serialization Tests")
class KeyValueLocationXmlTest extends AbstractXmlSerializationSuite<KeyValueLocation> {

    @Override
    protected Class<KeyValueLocation> type() {
        return KeyValueLocation.class;
    }

    @Override
    protected KeyValueLocation createDefault() {
        return KeyValueLocation.builder()
                .keyValueLocationType(new KeyValueLocationType(KeyValueLocationType.Standard.UNINTERPRETED_TEXT_STRING))
                .keyValueLocationValue(KeyValueLocationValue.builder().value("test").build())
                .build();
    }
}