package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.RevocationReasonCode;

public class RevocationReasonCodeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<RevocationReasonCode, String> {

    public RevocationReasonCodeJsonDeserializer() {
        super(RevocationReasonCode.kmipTag, RevocationReasonCode.encodingType, String.class, value -> RevocationReasonCode.fromName(value).inst());
    }
}