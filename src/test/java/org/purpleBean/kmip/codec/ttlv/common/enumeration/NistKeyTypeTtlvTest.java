package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.NistKeyType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("NistKeyType TTLV Serialization")
class NistKeyTypeTtlvTest extends AbstractTtlvSerializationTestSuite<NistKeyType> {
    @Override
    protected Class<NistKeyType> type() {
        return NistKeyType.class;
    }

    @Override
    protected NistKeyType createDefault() {
        return new NistKeyType(NistKeyType.Standard.PRIVATE_SIGNATURE_KEY);
    }

    @Override
    protected NistKeyType createVariant() {
        return new NistKeyType(NistKeyType.Standard.PUBLIC_SIGNATURE_VERIFICATION_KEY);
    }
}
