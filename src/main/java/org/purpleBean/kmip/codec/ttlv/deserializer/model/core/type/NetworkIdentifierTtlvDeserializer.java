package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.NetworkIdentifier;

public class NetworkIdentifierTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<NetworkIdentifier, String> {

    public NetworkIdentifierTtlvDeserializer() {
        super(NetworkIdentifier.kmipTag, NetworkIdentifier.encodingType, String.class, value -> NetworkIdentifier.builder().value(value).build());
    }
}