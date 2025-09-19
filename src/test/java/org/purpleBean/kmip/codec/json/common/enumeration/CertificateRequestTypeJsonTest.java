package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.CertificateRequestType;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("CertificateRequestType JSON Serialization")
class CertificateRequestTypeJsonTest extends AbstractJsonSerializationSuite<CertificateRequestType> {
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
        return new CertificateRequestType(CertificateRequestType.Standard.PROVISIONING);
    }
}
