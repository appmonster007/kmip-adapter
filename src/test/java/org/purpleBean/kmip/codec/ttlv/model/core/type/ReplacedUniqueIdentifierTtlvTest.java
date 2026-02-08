package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.ReplacedUniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ReplacedUniqueIdentifier TTLV Serialization Tests")
class ReplacedUniqueIdentifierTtlvTest extends AbstractTtlvSerializationTestSuite<ReplacedUniqueIdentifier> {

    @Override
    public Class<ReplacedUniqueIdentifier> type() {
        return ReplacedUniqueIdentifier.class;
    }

    @Override
    public ReplacedUniqueIdentifier createDefault() {
        return ReplacedUniqueIdentifier.builder().value("test-id").build();
    }

    @Override
    public ReplacedUniqueIdentifier createVariant() {
        return ReplacedUniqueIdentifier.builder().value("another-id").build();
    }
}