package org.purpleBean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.structure.X509CertificateSubject;
import org.purpleBean.kmip.model.core.type.SubjectAlternativeName;
import org.purpleBean.kmip.model.core.type.SubjectDistinguishedName;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("X509CertificateSubject Json Serialization Tests")
class X509CertificateSubjectJsonTest extends AbstractJsonSerializationTestSuite<X509CertificateSubject> {

    @Override
    public Class<X509CertificateSubject> type() {
        return X509CertificateSubject.class;
    }

    @Override
    public X509CertificateSubject createDefault() {
        return X509CertificateSubject.builder()
                .subjectDistinguishedName(
                        SubjectDistinguishedName.of("CN=Test Subject".getBytes())
                )
                .subjectAlternativeName(
                        SubjectAlternativeName.of("alt.subject.com".getBytes())
                )
                .build();
    }
}