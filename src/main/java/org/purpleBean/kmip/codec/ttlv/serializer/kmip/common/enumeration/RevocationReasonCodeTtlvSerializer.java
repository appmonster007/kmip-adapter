package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.RevocationReasonCode;

public class RevocationReasonCodeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<RevocationReasonCode, Integer> {

    public RevocationReasonCodeTtlvSerializer() {
        super(RevocationReasonCode::getValue);
    }
}