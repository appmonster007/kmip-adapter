package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.RevocationReasonCode;

public class RevocationReasonCodeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<RevocationReasonCode, Integer> {

    public RevocationReasonCodeTtlvDeserializer() {
        super(RevocationReasonCode.kmipTag, RevocationReasonCode.encodingType, Integer.class, value -> RevocationReasonCode.fromValue(value).inst());
    }
}