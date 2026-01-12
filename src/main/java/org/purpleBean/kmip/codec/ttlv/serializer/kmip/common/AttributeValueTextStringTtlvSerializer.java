package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.AttributeValueTextString;

public class AttributeValueTextStringTtlvSerializer extends AbstractKmipTtlvSerializer<AttributeValueTextString, String> {

    public AttributeValueTextStringTtlvSerializer() {
        super(AttributeValueTextString::getValue);
    }
}