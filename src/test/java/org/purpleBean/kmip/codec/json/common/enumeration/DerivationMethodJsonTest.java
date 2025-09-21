package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.DerivationMethod;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("DerivationMethod JSON Serialization")
class DerivationMethodJsonTest extends AbstractJsonSerializationSuite<DerivationMethod> {
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
