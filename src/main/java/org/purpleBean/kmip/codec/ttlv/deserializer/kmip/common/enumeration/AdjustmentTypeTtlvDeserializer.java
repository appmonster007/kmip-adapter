package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.AdjustmentType;

public class AdjustmentTypeTtlvDeserializer extends AbstractKmipTtlvDeserializer<AdjustmentType, Integer> {

    public AdjustmentTypeTtlvDeserializer() {
        super(AdjustmentType.kmipTag, AdjustmentType.encodingType, Integer.class, value -> new AdjustmentType(AdjustmentType.fromValue(value)));
    }
}