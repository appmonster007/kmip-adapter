package org.purpleBean.kmip.codec.xml.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.CertificateType;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("CertificateType XML Serialization")
class CertificateTypeXmlTest extends AbstractXmlSerializationSuite<CertificateType> {
    @Override
    protected Class<CertificateType> type() {
        return CertificateType.class;
    }

    @Override
    protected CertificateType createDefault() {
        return new CertificateType(CertificateType.Standard.X_509);
    }

    @Override
    protected CertificateType createVariant() {
        return new CertificateType(CertificateType.Standard.PGP);
    }
}
