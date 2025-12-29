package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.DeviceIdentifier;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("DeviceIdentifier XML Serialization Tests")
class DeviceIdentifierXmlTest extends AbstractXmlSerializationSuite<DeviceIdentifier> {

    @Override
    protected Class<DeviceIdentifier> type() {
        return DeviceIdentifier.class;
    }

    @Override
    protected DeviceIdentifier createDefault() {
        return DeviceIdentifier.builder().value("test-device-id").build();
    }

    @Override
    protected DeviceIdentifier createVariant() {
        return DeviceIdentifier.builder().value("another-device-id").build();
    }
}