package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.DeactivationReasonCode;

public class DeactivationReasonCodeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<DeactivationReasonCode, Integer> {

    public DeactivationReasonCodeTtlvSerializer() {
        super(DeactivationReasonCode::getValue);
    }
}