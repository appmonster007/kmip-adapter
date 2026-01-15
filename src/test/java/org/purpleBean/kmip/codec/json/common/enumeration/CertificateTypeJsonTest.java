package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.CertificateType;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CertificateType JSON Serialization")
class CertificateTypeJsonTest extends AbstractJsonSerializationTestSuite<CertificateType> {
    @Override
    protected Class<CertificateType> type() {
        return CertificateType.class;
    }

    @Override
    protected CertificateType createDefault() {
        return CertificateType.Standard.X_509.inst();
    }

    @Override
    protected CertificateType createVariant() {
        return CertificateType.Standard.PGP.inst();
    }
}
