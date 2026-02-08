package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.ReplacedUniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ReplacedUniqueIdentifier JSON Serialization Tests")
class ReplacedUniqueIdentifierJsonTest extends AbstractJsonSerializationTestSuite<ReplacedUniqueIdentifier> {

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