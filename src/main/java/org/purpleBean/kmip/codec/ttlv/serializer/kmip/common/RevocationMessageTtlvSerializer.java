package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.RevocationMessage;

public class RevocationMessageTtlvSerializer extends AbstractKmipTtlvSerializer<RevocationMessage, String> {

    public RevocationMessageTtlvSerializer() {
        super(RevocationMessage::getValue);
    }
}