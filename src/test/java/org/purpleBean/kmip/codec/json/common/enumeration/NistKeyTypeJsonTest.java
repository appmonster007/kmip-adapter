package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.NistKeyType;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationSuite;

@DisplayName("NistKeyType JSON Serialization")
class NistKeyTypeJsonTest extends AbstractJsonSerializationSuite<NistKeyType> {
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
