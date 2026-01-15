package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.NetworkIdentifier;

public class NetworkIdentifierJsonSerializer extends AbstractKmipDataTypeJsonSerializer<NetworkIdentifier, String> {

    public NetworkIdentifierJsonSerializer() {
        super(NetworkIdentifier::getValue);
    }
}