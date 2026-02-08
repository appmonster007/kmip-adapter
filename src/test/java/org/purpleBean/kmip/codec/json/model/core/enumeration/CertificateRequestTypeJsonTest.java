package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.CertificateRequestType;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertificateRequestType JSON Serialization")
class CertificateRequestTypeJsonTest extends AbstractJsonSerializationTestSuite<CertificateRequestType> {
    @Override
    public Class<CertificateRequestType> type() {
        return CertificateRequestType.class;
    }

    @Override
    public CertificateRequestType createDefault() {
        return CertificateRequestType.Standard.CRMF.inst();
    }

    @Override
    public CertificateRequestType createVariant() {
        return CertificateRequestType.Standard.PKCS_10.inst();
    }
}
