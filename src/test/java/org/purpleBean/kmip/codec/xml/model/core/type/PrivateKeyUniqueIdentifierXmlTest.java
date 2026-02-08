package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.PrivateKeyUniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("PrivateKeyUniqueIdentifier XML Serialization Tests")
class PrivateKeyUniqueIdentifierXmlTest extends AbstractXmlSerializationTestSuite<PrivateKeyUniqueIdentifier> {

    @Override
    public Class<PrivateKeyUniqueIdentifier> type() {
        return PrivateKeyUniqueIdentifier.class;
    }

    @Override
    public PrivateKeyUniqueIdentifier createDefault() {
        return PrivateKeyUniqueIdentifier.builder().value("test-private-key-id").build();
    }

    @Override
    public PrivateKeyUniqueIdentifier createVariant() {
        return PrivateKeyUniqueIdentifier.builder().value("another-private-key-id").build();
    }
}