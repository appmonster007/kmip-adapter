package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("UniqueIdentifier TTLV Serialization")
class UniqueIdentifierTtlvTest extends AbstractTtlvSerializationTestSuite<UniqueIdentifier> {
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
