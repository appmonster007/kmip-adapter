package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.NetworkIdentifier;

public class NetworkIdentifierXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<NetworkIdentifier, String> {

    public NetworkIdentifierXmlDeserializer() {
        super(NetworkIdentifier.kmipTag, NetworkIdentifier.encodingType, String.class, value -> NetworkIdentifier.builder().value(value).build());
    }
}