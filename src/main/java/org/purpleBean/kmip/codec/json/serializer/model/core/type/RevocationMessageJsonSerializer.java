package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.RevocationMessage;

public class RevocationMessageJsonSerializer extends AbstractKmipDataTypeJsonSerializer<RevocationMessage, String> {

    public RevocationMessageJsonSerializer() {
        super(RevocationMessage::getValue);
    }
}