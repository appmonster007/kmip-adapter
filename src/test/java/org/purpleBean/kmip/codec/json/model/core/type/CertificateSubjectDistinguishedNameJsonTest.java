package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.CertificateSubjectDistinguishedName;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertificateSubjectDistinguishedName JSON Serialization Tests")
class CertificateSubjectDistinguishedNameJsonTest extends AbstractJsonSerializationTestSuite<CertificateSubjectDistinguishedName> {

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