package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.AttributeValueInterval;

public class AttributeValueIntervalJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AttributeValueInterval, Integer> {

    public AttributeValueIntervalJsonSerializer() {
        super(AttributeValueInterval::getValue);
    }
}