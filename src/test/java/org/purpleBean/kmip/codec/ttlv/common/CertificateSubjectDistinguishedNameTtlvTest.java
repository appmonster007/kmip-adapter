package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.CertificateSubjectDistinguishedName;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CertificateSubjectDistinguishedName TTLV Serialization Tests")
class CertificateSubjectDistinguishedNameTtlvTest extends AbstractTtlvSerializationTestSuite<CertificateSubjectDistinguishedName> {

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