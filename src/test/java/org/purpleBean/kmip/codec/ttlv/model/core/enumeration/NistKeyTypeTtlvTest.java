package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.NistKeyType;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("NistKeyType TTLV Serialization")
class NistKeyTypeTtlvTest extends AbstractTtlvSerializationTestSuite<NistKeyType> {
    @Override
    protected Class<NistKeyType> type() {
        return NistKeyType.class;
    }

    @Override
    protected NistKeyType createDefault() {
        return NistKeyType.Standard.PRIVATE_SIGNATURE_KEY.inst();
    }

    @Override
    protected NistKeyType createVariant() {
        return NistKeyType.Standard.PUBLIC_SIGNATURE_VERIFICATION_KEY.inst();
    }
}
