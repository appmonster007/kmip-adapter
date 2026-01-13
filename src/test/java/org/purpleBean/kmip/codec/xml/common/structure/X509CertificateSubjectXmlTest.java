package org.purpleBean.kmip.codec.xml.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.SubjectAlternativeName;
import org.purpleBean.kmip.common.SubjectDistinguishedName;
import org.purpleBean.kmip.common.structure.X509CertificateSubject;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("X509CertificateSubject Xml Serialization Tests")
class X509CertificateSubjectXmlTest extends AbstractXmlSerializationTestSuite<X509CertificateSubject> {

    @Override
    protected Class<X509CertificateSubject> type() {
        return X509CertificateSubject.class;
    }

    @Override
    protected X509CertificateSubject createDefault() {
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