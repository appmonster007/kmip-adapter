package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.DigitalSignatureAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("DigitalSignatureAlgorithm JSON Serialization")
class DigitalSignatureAlgorithmJsonTest extends AbstractJsonSerializationTestSuite<DigitalSignatureAlgorithm> {
    @Override
    protected Class<DigitalSignatureAlgorithm> type() {
        return DigitalSignatureAlgorithm.class;
    }

    @Override
    protected DigitalSignatureAlgorithm createDefault() {
        return DigitalSignatureAlgorithm.Standard.MD2_WITH_RSA_ENCRYPTION.inst();
    }

    @Override
    protected DigitalSignatureAlgorithm createVariant() {
        return DigitalSignatureAlgorithm.Standard.MD5_WITH_RSA_ENCRYPTION.inst();
    }
}
