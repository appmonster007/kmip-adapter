package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.NetworkIdentifier;

public class NetworkIdentifierXmlSerializer extends AbstractKmipDataTypeXmlSerializer<NetworkIdentifier, String> {

    public NetworkIdentifierXmlSerializer() {
        super(NetworkIdentifier::getValue);
    }
}