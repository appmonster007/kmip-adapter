package org.purpleBean.kmip.codec.ttlv.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.CertificateSubjectAlternativeName;
import org.purpleBean.kmip.common.CertificateSubjectDistinguishedName;
import org.purpleBean.kmip.common.structure.CertificateSubject;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("CertificateSubject Ttlv Serialization Tests")
class CertificateSubjectTtlvTest extends AbstractTtlvSerializationSuite<CertificateSubject> {

    @Override
    protected Class<CertificateSubject> type() {
        return CertificateSubject.class;
    }

    @Override
    protected CertificateSubject createDefault() {
        return CertificateSubject.builder()
                .certificateSubjectDistinguishedName(
                        CertificateSubjectDistinguishedName.of("CN=Test Subject")
                )
                .certificateSubjectAlternativeName(
                        CertificateSubjectAlternativeName.of("alt.subject.com")
                )
                .build();
    }
}