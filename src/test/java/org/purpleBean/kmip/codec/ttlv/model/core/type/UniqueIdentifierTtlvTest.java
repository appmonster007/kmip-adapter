package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("UniqueIdentifier TTLV Serialization Tests")
class UniqueIdentifierTtlvTest extends AbstractTtlvSerializationTestSuite<UniqueIdentifier> {

    @Override
    public Class<UniqueIdentifier> type() {
        return UniqueIdentifier.class;
    }

    @Override
    public UniqueIdentifier createDefault() {
        return UniqueIdentifier.builder().value("FIXED_STRING").build();
    }

    @Override
    public UniqueIdentifier createVariant() {
        return UniqueIdentifier.builder().value("VARIANT_STRING").build();
    }
}
