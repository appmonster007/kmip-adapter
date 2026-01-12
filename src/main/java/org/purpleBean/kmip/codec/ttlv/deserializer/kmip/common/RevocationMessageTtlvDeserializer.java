package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.RevocationMessage;

public class RevocationMessageTtlvDeserializer extends AbstractKmipTtlvDeserializer<RevocationMessage, String> {

    public RevocationMessageTtlvDeserializer() {
        super(RevocationMessage.kmipTag, RevocationMessage.encodingType, String.class, value -> RevocationMessage.builder().value(value).build());
    }
}