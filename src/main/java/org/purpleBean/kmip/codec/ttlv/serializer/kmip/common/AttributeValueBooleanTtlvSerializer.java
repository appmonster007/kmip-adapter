package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.AttributeValueBoolean;

public class AttributeValueBooleanTtlvSerializer extends AbstractKmipTtlvSerializer<AttributeValueBoolean, Boolean> {

    public AttributeValueBooleanTtlvSerializer() {
        super(AttributeValueBoolean::getValue);
    }
}