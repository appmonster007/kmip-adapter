package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.CertificateSubjectAlternativeName;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CertificateSubjectAlternativeName TTLV Serialization Tests")
class CertificateSubjectAlternativeNameTtlvTest extends AbstractTtlvSerializationTestSuite<CertificateSubjectAlternativeName> {

    @Override
    protected Class<CertificateSubjectAlternativeName> type() {
        return CertificateSubjectAlternativeName.class;
    }

    @Override
    protected CertificateSubjectAlternativeName createDefault() {
        return CertificateSubjectAlternativeName.builder().value("test-subject-alt-name").build();
    }

    @Override
    protected CertificateSubjectAlternativeName createVariant() {
        return CertificateSubjectAlternativeName.builder().value("another-subject-alt-name").build();
    }
}