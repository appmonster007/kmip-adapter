package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.ReplacedUniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ReplacedUniqueIdentifier XML Serialization Tests")
class ReplacedUniqueIdentifierXmlTest extends AbstractXmlSerializationTestSuite<ReplacedUniqueIdentifier> {

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