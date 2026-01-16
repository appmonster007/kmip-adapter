package org.purpleBean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

import java.nio.ByteBuffer;

@DisplayName("AttestationAssertion Domain Tests")
class AttestationAssertionTest extends AbstractKmipDataTypeTestSuite<AttestationAssertion> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<AttestationAssertion> type() {
        return AttestationAssertion.class;
    }

    @Override
    protected AttestationAssertion createDefault() {
        return AttestationAssertion.of(ByteBuffer.wrap(new byte[]{0x01, 0x02, 0x03}));
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.BYTE_STRING;
    }
}