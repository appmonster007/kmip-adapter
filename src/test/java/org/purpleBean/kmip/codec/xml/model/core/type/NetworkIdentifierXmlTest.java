package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.NetworkIdentifier;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("NetworkIdentifier XML Serialization Tests")
class NetworkIdentifierXmlTest extends AbstractXmlSerializationTestSuite<NetworkIdentifier> {

    @Override
    public Class<NetworkIdentifier> type() {
        return NetworkIdentifier.class;
    }

    @Override
    public NetworkIdentifier createDefault() {
        return NetworkIdentifier.builder().value("test-network-id").build();
    }

    @Override
    public NetworkIdentifier createVariant() {
        return NetworkIdentifier.builder().value("another-network-id").build();
    }
}