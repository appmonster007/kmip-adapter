package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.NetworkIdentifier;

public class NetworkIdentifierJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<NetworkIdentifier, String> {

    public NetworkIdentifierJsonDeserializer() {
        super(NetworkIdentifier.kmipTag, NetworkIdentifier.encodingType, String.class, value -> NetworkIdentifier.builder().value(value).build());
    }
}