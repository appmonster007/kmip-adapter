package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.RevocationMessage;

public class RevocationMessageTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<RevocationMessage, String> {

    public RevocationMessageTtlvDeserializer() {
        super(RevocationMessage.kmipTag, RevocationMessage.encodingType, String.class, value -> RevocationMessage.builder().value(value).build());
    }
}