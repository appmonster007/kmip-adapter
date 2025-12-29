package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.DeviceIdentifier;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

@DisplayName("DeviceIdentifier TTLV Serialization Tests")
class DeviceIdentifierTtlvTest extends AbstractTtlvSerializationSuite<DeviceIdentifier> {

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