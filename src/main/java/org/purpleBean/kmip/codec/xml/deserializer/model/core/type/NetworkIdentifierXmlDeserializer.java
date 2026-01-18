package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.NetworkIdentifier;

public class NetworkIdentifierXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<NetworkIdentifier, String> {

    public NetworkIdentifierXmlDeserializer() {
        super(NetworkIdentifier.kmipTag, NetworkIdentifier.encodingType, String.class, value -> NetworkIdentifier.builder().value(value).build());
    }
}