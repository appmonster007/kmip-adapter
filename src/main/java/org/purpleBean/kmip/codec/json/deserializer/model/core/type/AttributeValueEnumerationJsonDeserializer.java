package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipEnumeration;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueEnumeration;
import org.purpleBean.kmip.util.StringUtils;

import java.io.IOException;

public class AttributeValueEnumerationJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AttributeValueEnumeration, AttributeValueEnumeration.AttributeValueEnumerationBuilder> {

    public AttributeValueEnumerationJsonDeserializer() {
        super(AttributeValueEnumeration.kmipTag, AttributeValueEnumeration.encodingType);
    }

    @Override
    protected AttributeValueEnumeration.AttributeValueEnumerationBuilder createBuilder() {
        return AttributeValueEnumeration.builder();
    }

    @Override
    protected void setValue(AttributeValueEnumeration.AttributeValueEnumerationBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        String attributeName = (String) ctxt.getAttribute("attributeName");
        KmipTag.Value enumType = KmipTag.fromName(StringUtils.covertTitleToPascalCase(attributeName));
        if (enumType == null) {
            ctxt.reportInputMismatch(handledType(), "Invalid attribute name for " + handledType().getSimpleName());
            return;
        }
        KmipEnumeration.Value<?> value = KmipEnumeration.getFromName(enumType).apply(ctxt.readValue(p, String.class));
        builder.value(value);
    }

    @Override
    protected AttributeValueEnumeration build(AttributeValueEnumeration.AttributeValueEnumerationBuilder builder) {
        return builder.build();
    }
}
