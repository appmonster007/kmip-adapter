package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.RevocationReasonCode;

public class RevocationReasonCodeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<RevocationReasonCode, String> {

    public RevocationReasonCodeJsonDeserializer() {
        super(RevocationReasonCode.kmipTag, RevocationReasonCode.encodingType, String.class, value -> new RevocationReasonCode(RevocationReasonCode.fromName(value)));
    }
}