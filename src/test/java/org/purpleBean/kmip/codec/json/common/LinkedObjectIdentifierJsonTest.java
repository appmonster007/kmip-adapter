package org.purpleBean.kmip.codec.json.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.LinkedObjectIdentifier;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("LinkedObjectIdentifier JSON Serialization Tests")
class LinkedObjectIdentifierJsonTest extends AbstractJsonSerializationTestSuite<LinkedObjectIdentifier> {

    @Override
    protected Class<LinkedObjectIdentifier> type() {
        return LinkedObjectIdentifier.class;
    }

    @Override
    protected LinkedObjectIdentifier createDefault() {
        return LinkedObjectIdentifier.builder().value("test-linked-id").build();
    }

    @Override
    protected LinkedObjectIdentifier createVariant() {
        return LinkedObjectIdentifier.builder().value("another-linked-id").build();
    }
}