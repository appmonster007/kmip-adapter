package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.AdjustmentType;

public class AdjustmentTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AdjustmentType, String> {

    public AdjustmentTypeJsonSerializer() {
        super(AdjustmentType::getDescription);
    }
}