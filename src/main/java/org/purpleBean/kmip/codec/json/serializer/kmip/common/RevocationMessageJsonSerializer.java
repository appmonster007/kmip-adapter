package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.RevocationMessage;

public class RevocationMessageJsonSerializer extends AbstractKmipDataTypeJsonSerializer<RevocationMessage, String> {

    public RevocationMessageJsonSerializer() {
        super(RevocationMessage::getValue);
    }
}