package org.purpleBean.kmip.codec.xml.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.X509CertificateSubject;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

@DisplayName("X509CertificateSubject Xml Serialization Tests")
class X509CertificateSubjectXmlTest extends AbstractXmlSerializationSuite<X509CertificateSubject> {

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