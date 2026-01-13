package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.AdjustmentType;

public class AdjustmentTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AdjustmentType, String> {

    public AdjustmentTypeJsonDeserializer() {
        super(AdjustmentType.kmipTag, AdjustmentType.encodingType, String.class, value -> new AdjustmentType(AdjustmentType.fromName(value)));
    }
}