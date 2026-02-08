package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.DigitalSignatureAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DigitalSignatureAlgorithm TTLV Serialization")
class DigitalSignatureAlgorithmTtlvTest extends AbstractTtlvSerializationTestSuite<DigitalSignatureAlgorithm> {
    @Override
    public Class<DigitalSignatureAlgorithm> type() {
        return DigitalSignatureAlgorithm.class;
    }

    @Override
    public DigitalSignatureAlgorithm createDefault() {
        return DigitalSignatureAlgorithm.Standard.MD2_WITH_RSA_ENCRYPTION.inst();
    }

    @Override
    public DigitalSignatureAlgorithm createVariant() {
        return DigitalSignatureAlgorithm.Standard.MD5_WITH_RSA_ENCRYPTION.inst();
    }
}
