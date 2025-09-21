package org.purpleBean.kmip.codec.ttlv.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.DerivationMethod;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("DerivationMethod TTLV Serialization")
class DerivationMethodTtlvTest extends AbstractTtlvSerializationSuite<DerivationMethod> {
    @Override
    protected Class<DerivationMethod> type() {
        return DerivationMethod.class;
    }

    @Override
    protected DerivationMethod createDefault() {
        return new DerivationMethod(DerivationMethod.Standard.PBKDF2);
    }

    @Override
    protected DerivationMethod createVariant() {
        return new DerivationMethod(DerivationMethod.Standard.HASH);
    }
}
