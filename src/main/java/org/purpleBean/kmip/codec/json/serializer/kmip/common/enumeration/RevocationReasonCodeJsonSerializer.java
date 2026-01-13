package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.RevocationReasonCode;

public class RevocationReasonCodeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<RevocationReasonCode, String> {

    public RevocationReasonCodeJsonSerializer() {
        super(RevocationReasonCode::getDescription);
    }
}