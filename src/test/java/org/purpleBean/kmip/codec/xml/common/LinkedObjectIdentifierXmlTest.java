package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.LinkedObjectIdentifier;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("LinkedObjectIdentifier XML Serialization Tests")
class LinkedObjectIdentifierXmlTest extends AbstractXmlSerializationTestSuite<LinkedObjectIdentifier> {

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