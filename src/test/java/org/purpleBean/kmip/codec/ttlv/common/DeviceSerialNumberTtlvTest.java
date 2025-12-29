package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.DeviceSerialNumber;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

@DisplayName("DeviceSerialNumber TTLV Serialization Tests")
class DeviceSerialNumberTtlvTest extends AbstractTtlvSerializationSuite<DeviceSerialNumber> {

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