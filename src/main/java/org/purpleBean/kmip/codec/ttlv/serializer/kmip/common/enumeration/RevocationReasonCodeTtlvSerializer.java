package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.RevocationReasonCode;

public class RevocationReasonCodeTtlvSerializer extends AbstractKmipTtlvSerializer<RevocationReasonCode, Integer> {

    public RevocationReasonCodeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}