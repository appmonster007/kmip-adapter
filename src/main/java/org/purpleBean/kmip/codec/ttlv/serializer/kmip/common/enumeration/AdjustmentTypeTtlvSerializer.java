package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.AdjustmentType;

public class AdjustmentTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AdjustmentType, Integer> {

    public AdjustmentTypeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}