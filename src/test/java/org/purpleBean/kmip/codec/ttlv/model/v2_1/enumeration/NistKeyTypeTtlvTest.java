package org.purpleBean.kmip.codec.ttlv.model.v2_1.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.enumeration.NistKeyType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("NistKeyType TTLV Serialization")
class NistKeyTypeTtlvTest extends AbstractTtlvSerializationTestSuite<NistKeyType> {
    @Override
    public Class<NistKeyType> type() {
        return NistKeyType.class;
    }

    @Override
    public NistKeyType createDefault() {
        return NistKeyType.Standard.PRIVATE_SIGNATURE_KEY.inst();
    }

    @Override
    public NistKeyType createVariant() {
        return NistKeyType.Standard.PUBLIC_SIGNATURE_VERIFICATION_KEY.inst();
    }
}
