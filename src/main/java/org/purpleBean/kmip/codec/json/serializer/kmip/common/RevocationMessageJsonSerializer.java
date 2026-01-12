package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.RevocationMessage;

public class RevocationMessageJsonSerializer extends AbstractKmipJsonSerializer<RevocationMessage, String> {

    public RevocationMessageJsonSerializer() {
        super(RevocationMessage::getValue);
    }
}