package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.CertificateRequestType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("CertificateRequestType XML Serialization")
class CertificateRequestTypeXmlTest extends AbstractXmlSerializationSuite<CertificateRequestType> {
    @Override
    protected Class<CertificateRequestType> type() {
        return CertificateRequestType.class;
    }

    @Override
    protected CertificateRequestType createDefault() {
        return new CertificateRequestType(CertificateRequestType.Standard.PLACEHOLDER_1);
    }

    @Override
    protected CertificateRequestType createVariant() {
        return new CertificateRequestType(CertificateRequestType.Standard.PLACEHOLDER_2);
    }
}
