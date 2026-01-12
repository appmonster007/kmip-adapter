package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.RevocationReasonCode;

public class RevocationReasonCodeTtlvDeserializer extends AbstractKmipTtlvDeserializer<RevocationReasonCode, Integer> {

    public RevocationReasonCodeTtlvDeserializer() {
        super(RevocationReasonCode.kmipTag, RevocationReasonCode.encodingType, Integer.class, value -> new RevocationReasonCode(RevocationReasonCode.fromValue(value)));
    }
}