package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.NetworkIdentifier;

public class NetworkIdentifierJsonDeserializer extends AbstractKmipJsonDeserializer<NetworkIdentifier, String> {

    public NetworkIdentifierJsonDeserializer() {
        super(NetworkIdentifier.kmipTag, NetworkIdentifier.encodingType, String.class, value -> NetworkIdentifier.builder().value(value).build());
    }
}