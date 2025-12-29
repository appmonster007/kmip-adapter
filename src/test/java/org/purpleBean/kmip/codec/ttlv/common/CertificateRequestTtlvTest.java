package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.CertificateRequest;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;

import java.nio.ByteBuffer;

@DisplayName("CertificateRequest TTLV Serialization Tests")
class CertificateRequestTtlvTest extends AbstractTtlvSerializationSuite<CertificateRequest> {

    @Override
    protected Class<CertificateRequest> type() {
        return CertificateRequest.class;
    }

    @Override
    protected CertificateRequest createDefault() {
        return CertificateRequest.of(new byte[]{0x01, 0x02, 0x03});
    }

    @Override
    protected CertificateRequest createVariant() {
        return CertificateRequest.of(new byte[]{0x04, 0x05, 0x06});
    }
}