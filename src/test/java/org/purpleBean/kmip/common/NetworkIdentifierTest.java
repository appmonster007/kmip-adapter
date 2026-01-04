package org.purpleBean.kmip.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeSuite;

@DisplayName("NetworkIdentifier Domain Tests")
class NetworkIdentifierTest extends AbstractKmipDataTypeSuite<NetworkIdentifier> {

    @Override
    protected Class<NetworkIdentifier> type() {
        return NetworkIdentifier.class;
    }

    @Override
    protected NetworkIdentifier createDefault() {
        return NetworkIdentifier.builder().value("test-network-id").build();
    }

    @Override
    protected EncodingType expectedEncodingType() {
        return EncodingType.TEXT_STRING;
    }
}