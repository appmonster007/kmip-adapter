package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.AttributeValueTextString;

public class AttributeValueTextStringJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AttributeValueTextString, String> {

    public AttributeValueTextStringJsonSerializer() {
        super(AttributeValueTextString::getValue);
    }
}