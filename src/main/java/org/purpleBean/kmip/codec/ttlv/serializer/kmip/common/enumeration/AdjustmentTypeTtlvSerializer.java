package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.AdjustmentType;

public class AdjustmentTypeTtlvSerializer extends AbstractKmipTtlvSerializer<AdjustmentType, Integer> {
    public AdjustmentTypeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}