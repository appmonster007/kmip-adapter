package org.purpleBean.kmip.codec.xml.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.Issuer;
import org.purpleBean.kmip.common.SerialNumber;
import org.purpleBean.kmip.common.structure.CertificateIdentifier;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CertificateIdentifier Xml Serialization Tests")
class CertificateIdentifierXmlTest extends AbstractXmlSerializationTestSuite<CertificateIdentifier> {

    @Override
    protected Class<CertificateIdentifier> type() {
        return CertificateIdentifier.class;
    }

    @Override
    protected CertificateIdentifier createDefault() {
        return CertificateIdentifier.builder()
                .issuer(Issuer.of("CN=Test Issuer"))
                .serialNumber(SerialNumber.of("12345"))
                .build();
    }
}