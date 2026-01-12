package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.AttributeValueTextString;

public class AttributeValueTextStringJsonSerializer extends AbstractKmipJsonSerializer<AttributeValueTextString, String> {

    public AttributeValueTextStringJsonSerializer() {
        super(AttributeValueTextString::getValue);
    }
}