package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.AdjustmentType;

public class AdjustmentTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AdjustmentType, Integer> {

    public AdjustmentTypeTtlvSerializer() {
        super(AdjustmentType::getIntValue);
    }
}