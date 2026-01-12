package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.NetworkIdentifier;

public class NetworkIdentifierXmlSerializer extends AbstractKmipXmlSerializer<NetworkIdentifier, String> {

    public NetworkIdentifierXmlSerializer() {
        super(NetworkIdentifier::getValue);
    }
}