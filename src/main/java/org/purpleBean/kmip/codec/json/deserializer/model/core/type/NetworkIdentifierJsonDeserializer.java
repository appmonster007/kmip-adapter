package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.NetworkIdentifier;

public class NetworkIdentifierJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<NetworkIdentifier, String> {

    public NetworkIdentifierJsonDeserializer() {
        super(NetworkIdentifier.kmipTag, NetworkIdentifier.encodingType, String.class, value -> NetworkIdentifier.builder().value(value).build());
    }
}