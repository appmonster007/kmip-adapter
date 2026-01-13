package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.RevocationMessage;

public class RevocationMessageXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<RevocationMessage, String> {

    public RevocationMessageXmlDeserializer() {
        super(RevocationMessage.kmipTag, RevocationMessage.encodingType, String.class, value -> RevocationMessage.builder().value(value).build());
    }
}