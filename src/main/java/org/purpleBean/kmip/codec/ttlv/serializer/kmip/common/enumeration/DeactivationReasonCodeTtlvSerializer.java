package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.DeactivationReasonCode;

public class DeactivationReasonCodeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<DeactivationReasonCode, Integer> {

    public DeactivationReasonCodeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}