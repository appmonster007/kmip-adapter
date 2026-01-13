package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.AttributeValueBoolean;

public class AttributeValueBooleanTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AttributeValueBoolean, Boolean> {

    public AttributeValueBooleanTtlvSerializer() {
        super(AttributeValueBoolean::getValue);
    }
}