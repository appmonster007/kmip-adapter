package org.purpleBean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.model.core.type.DeviceSerialNumber;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("DeviceSerialNumber Domain Tests")
class DeviceSerialNumberTest extends AbstractKmipDataTypeTestSuite<DeviceSerialNumber> {

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