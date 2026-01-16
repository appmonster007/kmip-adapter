package org.purpleBean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.DeviceCredential;
import org.purpleBean.kmip.model.core.type.DeviceSerialNumber;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("DeviceCredential Json Serialization Tests")
class DeviceCredentialJsonTest extends AbstractJsonSerializationTestSuite<DeviceCredential> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<DeviceCredential> type() {
        return DeviceCredential.class;
    }

    @Override
    protected DeviceCredential createDefault() {
        return DeviceCredential.builder()
                .deviceSerialNumber(DeviceSerialNumber.of("test-serial-number"))
                .build();
    }

    @Override
    protected DeviceCredential createVariant() {
        return DeviceCredential.builder()
                .deviceSerialNumber(DeviceSerialNumber.of("test-serial-number-variant"))
                .build();
    }
}