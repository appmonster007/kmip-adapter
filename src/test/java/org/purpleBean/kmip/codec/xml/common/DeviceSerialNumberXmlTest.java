package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.DeviceSerialNumber;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("DeviceSerialNumber XML Serialization Tests")
class DeviceSerialNumberXmlTest extends AbstractXmlSerializationTestSuite<DeviceSerialNumber> {

    @Override
    protected Class<DeviceSerialNumber> type() {
        return DeviceSerialNumber.class;
    }

    @Override
    protected DeviceSerialNumber createDefault() {
        return DeviceSerialNumber.builder().value("12345").build();
    }

    @Override
    protected DeviceSerialNumber createVariant() {
        return DeviceSerialNumber.builder().value("67890").build();
    }
}