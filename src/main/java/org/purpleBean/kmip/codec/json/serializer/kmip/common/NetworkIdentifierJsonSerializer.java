package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.NetworkIdentifier;

public class NetworkIdentifierJsonSerializer extends AbstractKmipJsonSerializer<NetworkIdentifier, String> {

    public NetworkIdentifierJsonSerializer() {
        super(NetworkIdentifier::getValue);
    }
}