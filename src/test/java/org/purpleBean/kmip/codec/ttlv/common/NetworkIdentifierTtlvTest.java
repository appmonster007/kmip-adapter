package org.purpleBean.kmip.codec.ttlv.common;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.NetworkIdentifier;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationSuite;

@DisplayName("NetworkIdentifier TTLV Serialization Tests")
class NetworkIdentifierTtlvTest extends AbstractTtlvSerializationSuite<NetworkIdentifier> {

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