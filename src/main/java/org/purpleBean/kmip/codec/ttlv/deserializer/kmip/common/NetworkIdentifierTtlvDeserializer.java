package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.NetworkIdentifier;

public class NetworkIdentifierTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<NetworkIdentifier, String> {

    public NetworkIdentifierTtlvDeserializer() {
        super(NetworkIdentifier.kmipTag, NetworkIdentifier.encodingType, String.class, value -> NetworkIdentifier.builder().value(value).build());
    }
}