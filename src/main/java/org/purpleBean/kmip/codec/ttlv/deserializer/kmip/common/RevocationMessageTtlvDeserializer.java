package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.RevocationMessage;

public class RevocationMessageTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<RevocationMessage, String> {

    public RevocationMessageTtlvDeserializer() {
        super(RevocationMessage.kmipTag, RevocationMessage.encodingType, String.class, value -> RevocationMessage.builder().value(value).build());
    }
}