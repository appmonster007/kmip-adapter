package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.AttestationMeasurement;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

import java.nio.ByteBuffer;

@DisplayName("AttestationMeasurement Xml Serialization Tests")
class AttestationMeasurementXmlTest extends AbstractXmlSerializationTestSuite<AttestationMeasurement> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<AttestationMeasurement> type() {
        return AttestationMeasurement.class;
    }

    @Override
    protected AttestationMeasurement createDefault() {
        return AttestationMeasurement.of(ByteBuffer.wrap(new byte[]{0x01, 0x02, 0x03}));  // TODO: Create a default instance
    }

    @Override
    protected AttestationMeasurement createVariant() {
        return AttestationMeasurement.of(ByteBuffer.wrap(new byte[]{0x04, 0x05, 0x06}));  // TODO: Create a variant instance
    }
}