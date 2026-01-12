package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.RevocationReasonCode;

public class RevocationReasonCodeJsonSerializer extends AbstractKmipJsonSerializer<RevocationReasonCode, String> {

    public RevocationReasonCodeJsonSerializer() {
        super(RevocationReasonCode::getDescription);
    }
}