package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.CertificateRequestType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CertificateRequestType TTLV Serialization")
class CertificateRequestTypeTtlvTest extends AbstractTtlvSerializationTestSuite<CertificateRequestType> {
    @Override
    protected Class<CertificateRequestType> type() {
        return CertificateRequestType.class;
    }

    @Override
    protected CertificateRequestType createDefault() {
        return CertificateRequestType.Standard.CRMF.inst();
    }

    @Override
    protected CertificateRequestType createVariant() {
        return CertificateRequestType.Standard.PKCS_10.inst();
    }
}
