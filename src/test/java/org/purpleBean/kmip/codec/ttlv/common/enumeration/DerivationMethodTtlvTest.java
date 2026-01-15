package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.DerivationMethod;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DerivationMethod TTLV Serialization")
class DerivationMethodTtlvTest extends AbstractTtlvSerializationTestSuite<DerivationMethod> {
    @Override
    protected Class<DerivationMethod> type() {
        return DerivationMethod.class;
    }

    @Override
    protected DerivationMethod createDefault() {
        return DerivationMethod.Standard.PBKDF2.inst();
    }

    @Override
    protected DerivationMethod createVariant() {
        return DerivationMethod.Standard.HASH.inst();
    }
}
