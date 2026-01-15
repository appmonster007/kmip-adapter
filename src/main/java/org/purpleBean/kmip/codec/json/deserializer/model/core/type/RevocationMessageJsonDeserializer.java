package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.RevocationMessage;

public class RevocationMessageJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<RevocationMessage, String> {

    public RevocationMessageJsonDeserializer() {
        super(RevocationMessage.kmipTag, RevocationMessage.encodingType, String.class, value -> RevocationMessage.builder().value(value).build());
    }
}