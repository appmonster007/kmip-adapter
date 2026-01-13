package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.AttributeValueTextString;

public class AttributeValueTextStringTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AttributeValueTextString, String> {

    public AttributeValueTextStringTtlvSerializer() {
        super(AttributeValueTextString::getValue);
    }
}