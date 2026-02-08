package org.purpleBean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.CertificateType;
import org.purpleBean.kmip.model.core.structure.Certificate;
import org.purpleBean.kmip.model.core.type.CertificateValue;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("Certificate Json Serialization Tests")
class CertificateJsonTest extends AbstractJsonSerializationTestSuite<Certificate> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    public Class<Certificate> type() {
        return Certificate.class;
    }

    @Override
    public Certificate createDefault() {
        return Certificate.builder()
                .certificateType(CertificateType.Standard.X_509.inst())
                .certificateValue(CertificateValue.of(new byte[0]))
                .build();
    }

    @Override
    public Certificate createVariant() {
        return Certificate.builder()
                .certificateType(CertificateType.Standard.PGP.inst())
                .certificateValue(CertificateValue.of(new byte[1]))
                .build();
    }
}