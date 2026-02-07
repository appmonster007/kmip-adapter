package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.AttestationAssertion;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.nio.ByteBuffer;

@DisplayName("AttestationAssertion Ttlv Serialization Tests")
class AttestationAssertionTtlvTest extends AbstractTtlvSerializationTestSuite<AttestationAssertion> {

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
    protected AttestationAssertion createVariant() {
        return AttestationAssertion.of(ByteBuffer.wrap(new byte[]{0x04, 0x05, 0x06}));
    }
}