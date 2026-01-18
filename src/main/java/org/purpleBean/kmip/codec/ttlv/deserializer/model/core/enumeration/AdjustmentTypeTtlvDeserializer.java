package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.AdjustmentType;

public class AdjustmentTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AdjustmentType, Integer> {

    public AdjustmentTypeTtlvDeserializer() {
        super(AdjustmentType.kmipTag, AdjustmentType.encodingType, Integer.class, value -> new AdjustmentType(AdjustmentType.fromValue(value)));
    }
}