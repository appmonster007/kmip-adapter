package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.ReplacedUniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("ReplacedUniqueIdentifier TTLV Serialization Tests")
class ReplacedUniqueIdentifierTtlvTest extends AbstractTtlvSerializationSuite<ReplacedUniqueIdentifier> {

    @Override
    protected Class<ReplacedUniqueIdentifier> type() {
        return ReplacedUniqueIdentifier.class;
    }

    @Override
    protected ReplacedUniqueIdentifier createDefault() {
        return ReplacedUniqueIdentifier.builder().value("test-id").build();
    }

    @Override
    protected ReplacedUniqueIdentifier createVariant() {
        return ReplacedUniqueIdentifier.builder().value("another-id").build();
    }
}