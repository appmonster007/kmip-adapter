package org.purpleBean.kmip.codec.json.common.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.enumeration.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("UniqueIdentifier JSON Serialization")
class UniqueIdentifierJsonTest extends AbstractJsonSerializationTestSuite<UniqueIdentifier> {
    @Override
    protected Class<UniqueIdentifier> type() {
        return UniqueIdentifier.class;
    }

    @Override
    protected UniqueIdentifier createDefault() {
        return UniqueIdentifier.Standard.ID_PLACEHOLDER.inst();
    }

    @Override
    protected UniqueIdentifier createVariant() {
        return UniqueIdentifier.Standard.CERTIFY.inst();
    }
}
