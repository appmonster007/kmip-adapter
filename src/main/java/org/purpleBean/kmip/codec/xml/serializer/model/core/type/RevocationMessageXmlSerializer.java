package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.RevocationMessage;

public class RevocationMessageXmlSerializer extends AbstractKmipDataTypeXmlSerializer<RevocationMessage, String> {

    public RevocationMessageXmlSerializer() {
        super(RevocationMessage::getValue);
    }
}