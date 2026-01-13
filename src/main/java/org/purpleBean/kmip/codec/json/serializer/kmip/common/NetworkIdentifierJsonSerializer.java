package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.NetworkIdentifier;

public class NetworkIdentifierJsonSerializer extends AbstractKmipDataTypeJsonSerializer<NetworkIdentifier, String> {

    public NetworkIdentifierJsonSerializer() {
        super(NetworkIdentifier::getValue);
    }
}