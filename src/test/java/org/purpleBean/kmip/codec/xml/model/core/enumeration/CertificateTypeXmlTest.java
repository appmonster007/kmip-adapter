package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.CertificateType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CertificateType XML Serialization")
class CertificateTypeXmlTest extends AbstractXmlSerializationTestSuite<CertificateType> {
    @Override
    protected Class<CertificateType> type() {
        return CertificateType.class;
    }

    @Override
    protected CertificateType createDefault() {
        return CertificateType.Standard.X_509.inst();
    }

    @Override
    protected CertificateType createVariant() {
        return CertificateType.Standard.PGP.inst();
    }
}
