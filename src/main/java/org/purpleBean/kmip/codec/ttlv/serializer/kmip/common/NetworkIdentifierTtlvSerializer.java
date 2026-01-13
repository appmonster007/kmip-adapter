package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.NetworkIdentifier;

public class NetworkIdentifierTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<NetworkIdentifier, String> {

    public NetworkIdentifierTtlvSerializer() {
        super(NetworkIdentifier::getValue);
    }
}