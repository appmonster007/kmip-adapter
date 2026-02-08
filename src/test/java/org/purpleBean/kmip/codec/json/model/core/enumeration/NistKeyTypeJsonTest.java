package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.NistKeyType;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("NistKeyType JSON Serialization")
class NistKeyTypeJsonTest extends AbstractJsonSerializationTestSuite<NistKeyType> {
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
