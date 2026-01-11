package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.RevocationMessage;

public class RevocationMessageJsonDeserializer extends AbstractKmipJsonDeserializer<RevocationMessage, String> {

    public RevocationMessageJsonDeserializer() {
        super(RevocationMessage.kmipTag, RevocationMessage.encodingType, String.class, value -> RevocationMessage.builder().value(value).build());
    }
}