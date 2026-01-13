package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.AttributeValueTextString;

public class AttributeValueTextStringJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AttributeValueTextString, String> {

    public AttributeValueTextStringJsonSerializer() {
        super(AttributeValueTextString::getValue);
    }
}