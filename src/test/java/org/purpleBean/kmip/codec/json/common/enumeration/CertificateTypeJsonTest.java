package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.CertificateType;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("CertificateType JSON Serialization")
class CertificateTypeJsonTest extends AbstractJsonSerializationSuite<CertificateType> {
    @Override
    protected Class<CertificateType> type() {
        return CertificateType.class;
    }

    @Override
    protected CertificateType createDefault() {
        return new CertificateType(CertificateType.Standard.PLACEHOLDER_1);
    }

    @Override
    protected CertificateType createVariant() {
        return new CertificateType(CertificateType.Standard.PLACEHOLDER_2);
    }
}
