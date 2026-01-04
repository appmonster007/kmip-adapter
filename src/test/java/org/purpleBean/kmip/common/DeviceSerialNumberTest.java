package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

@DisplayName("DeviceSerialNumber Domain Tests")
class DeviceSerialNumberTest extends AbstractKmipDataTypeSuite<DeviceSerialNumber> {

    @Override
    protected Class<DeviceSerialNumber> type() {
        return DeviceSerialNumber.class;
    }

    @Override
    protected DeviceSerialNumber createDefault() {
        return DeviceSerialNumber.builder().value("12345").build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.TEXT_STRING;
    }
}