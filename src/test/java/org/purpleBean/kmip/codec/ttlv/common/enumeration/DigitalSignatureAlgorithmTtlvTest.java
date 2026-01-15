package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.DigitalSignatureAlgorithm;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DigitalSignatureAlgorithm TTLV Serialization")
class DigitalSignatureAlgorithmTtlvTest extends AbstractTtlvSerializationTestSuite<DigitalSignatureAlgorithm> {
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
