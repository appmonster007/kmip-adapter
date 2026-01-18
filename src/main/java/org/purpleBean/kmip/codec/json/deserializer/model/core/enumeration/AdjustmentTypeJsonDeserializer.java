package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.AdjustmentType;

public class AdjustmentTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AdjustmentType, String> {

    public AdjustmentTypeJsonDeserializer() {
        super(AdjustmentType.kmipTag, AdjustmentType.encodingType, String.class, value -> new AdjustmentType(AdjustmentType.fromName(value)));
    }
}