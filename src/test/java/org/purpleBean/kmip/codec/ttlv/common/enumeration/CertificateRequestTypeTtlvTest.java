package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.CertificateRequestType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("CertificateRequestType TTLV Serialization")
class CertificateRequestTypeTtlvTest extends AbstractTtlvSerializationSuite<CertificateRequestType> {
    @Override
    protected Class<CertificateRequestType> type() {
        return CertificateRequestType.class;
    }

    @Override
    protected CertificateRequestType createDefault() {
        return new CertificateRequestType(CertificateRequestType.Standard.CRMF);
    }

    @Override
    protected CertificateRequestType createVariant() {
        return new CertificateRequestType(CertificateRequestType.Standard.PKCS_10);
    }
}
