package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.CertificateSubjectDistinguishedName;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CertificateSubjectDistinguishedName XML Serialization Tests")
class CertificateSubjectDistinguishedNameXmlTest extends AbstractXmlSerializationTestSuite<CertificateSubjectDistinguishedName> {

    @Override
    protected Class<CertificateSubjectDistinguishedName> type() {
        return CertificateSubjectDistinguishedName.class;
    }

    @Override
    protected CertificateSubjectDistinguishedName createDefault() {
        return CertificateSubjectDistinguishedName.builder().value("test-subject-dn").build();
    }

    @Override
    protected CertificateSubjectDistinguishedName createVariant() {
        return CertificateSubjectDistinguishedName.builder().value("another-subject-dn").build();
    }
}