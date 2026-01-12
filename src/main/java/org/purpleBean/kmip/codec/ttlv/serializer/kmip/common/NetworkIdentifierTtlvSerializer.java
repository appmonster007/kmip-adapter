package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.NetworkIdentifier;

public class NetworkIdentifierTtlvSerializer extends AbstractKmipTtlvSerializer<NetworkIdentifier, String> {

    public NetworkIdentifierTtlvSerializer() {
        super(NetworkIdentifier::getValue);
    }
}