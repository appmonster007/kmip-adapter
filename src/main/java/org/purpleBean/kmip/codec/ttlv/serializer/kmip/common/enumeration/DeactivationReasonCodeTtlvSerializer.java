package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.DeactivationReasonCode;

public class DeactivationReasonCodeTtlvSerializer extends AbstractKmipTtlvSerializer<DeactivationReasonCode, Integer> {

    public DeactivationReasonCodeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}