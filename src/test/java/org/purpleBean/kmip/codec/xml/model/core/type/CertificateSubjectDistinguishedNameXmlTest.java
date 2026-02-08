package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.CertificateSubjectDistinguishedName;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CertificateSubjectDistinguishedName XML Serialization Tests")
class CertificateSubjectDistinguishedNameXmlTest extends AbstractXmlSerializationTestSuite<CertificateSubjectDistinguishedName> {

    @Override
    public Class<CertificateSubjectDistinguishedName> type() {
        return CertificateSubjectDistinguishedName.class;
    }

    @Override
    public CertificateSubjectDistinguishedName createDefault() {
        return CertificateSubjectDistinguishedName.builder().value("test-subject-dn").build();
    }

    @Override
    public CertificateSubjectDistinguishedName createVariant() {
        return CertificateSubjectDistinguishedName.builder().value("another-subject-dn").build();
    }
}