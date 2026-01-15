package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.RevocationReasonCode;

public class RevocationReasonCodeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<RevocationReasonCode, String> {

    public RevocationReasonCodeJsonSerializer() {
        super(RevocationReasonCode::getDescription);
    }
}