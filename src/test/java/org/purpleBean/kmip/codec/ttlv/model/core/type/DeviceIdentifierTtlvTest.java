package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.DeviceIdentifier;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DeviceIdentifier TTLV Serialization Tests")
class DeviceIdentifierTtlvTest extends AbstractTtlvSerializationTestSuite<DeviceIdentifier> {

    @Override
    public Class<DeviceIdentifier> type() {
        return DeviceIdentifier.class;
    }

    @Override
    public DeviceIdentifier createDefault() {
        return DeviceIdentifier.builder().value("test-device-id").build();
    }

    @Override
    public DeviceIdentifier createVariant() {
        return DeviceIdentifier.builder().value("another-device-id").build();
    }
}