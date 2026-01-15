package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueTextString;

public class AttributeValueTextStringJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AttributeValueTextString, String> {

    public AttributeValueTextStringJsonDeserializer() {
        super(AttributeValueTextString.kmipTag, AttributeValueTextString.encodingType, String.class, value -> AttributeValueTextString.builder().value(value).build());
    }
}