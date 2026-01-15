package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.AttributeValueBoolean;

public class AttributeValueBooleanTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AttributeValueBoolean, Boolean> {

    public AttributeValueBooleanTtlvSerializer() {
        super(AttributeValueBoolean::getValue);
    }
}