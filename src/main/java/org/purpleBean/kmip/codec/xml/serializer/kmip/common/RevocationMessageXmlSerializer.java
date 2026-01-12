package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.RevocationMessage;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class RevocationMessageXmlSerializer extends AbstractKmipXmlSerializer<RevocationMessage, String> {

    public RevocationMessageXmlSerializer() {
        super(RevocationMessage::getValue);
    }
}