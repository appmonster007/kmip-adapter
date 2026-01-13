package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.CertificateSubjectAlternativeName;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertificateSubjectAlternativeName JSON Serialization Tests")
class CertificateSubjectAlternativeNameJsonTest extends AbstractJsonSerializationTestSuite<CertificateSubjectAlternativeName> {

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