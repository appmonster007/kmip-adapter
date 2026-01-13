package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.RevocationMessage;

public class RevocationMessageJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<RevocationMessage, String> {

    public RevocationMessageJsonDeserializer() {
        super(RevocationMessage.kmipTag, RevocationMessage.encodingType, String.class, value -> RevocationMessage.builder().value(value).build());
    }
}