package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.NetworkIdentifier;

public class NetworkIdentifierTtlvDeserializer extends AbstractKmipTtlvDeserializer<NetworkIdentifier, String> {

    public NetworkIdentifierTtlvDeserializer() {
        super(NetworkIdentifier.kmipTag, NetworkIdentifier.encodingType, String.class, value -> NetworkIdentifier.builder().value(value).build());
    }
}