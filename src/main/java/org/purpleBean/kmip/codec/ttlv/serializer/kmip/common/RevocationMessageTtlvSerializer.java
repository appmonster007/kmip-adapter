package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.RevocationMessage;

public class RevocationMessageTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<RevocationMessage, String> {

    public RevocationMessageTtlvSerializer() {
        super(RevocationMessage::getValue);
    }
}