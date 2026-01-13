package org.purpleBean.kmip.codec.xml.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.NetworkIdentifier;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("NetworkIdentifier XML Serialization Tests")
class NetworkIdentifierXmlTest extends AbstractXmlSerializationTestSuite<NetworkIdentifier> {

    @Override
    protected Class<NetworkIdentifier> type() {
        return NetworkIdentifier.class;
    }

    @Override
    protected NetworkIdentifier createDefault() {
        return NetworkIdentifier.builder().value("test-network-id").build();
    }

    @Override
    protected NetworkIdentifier createVariant() {
        return NetworkIdentifier.builder().value("another-network-id").build();
    }
}