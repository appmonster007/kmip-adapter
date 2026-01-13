package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.RevocationMessage;

public class RevocationMessageXmlSerializer extends AbstractKmipDataTypeXmlSerializer<RevocationMessage, String> {

    public RevocationMessageXmlSerializer() {
        super(RevocationMessage::getValue);
    }
}