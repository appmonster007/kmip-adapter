package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipEnumeration;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueEnumeration;
import org.purpleBean.kmip.util.StringUtils;

import java.io.IOException;

public class AttributeValueEnumerationXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AttributeValueEnumeration, AttributeValueEnumeration.AttributeValueEnumerationBuilder> {

    public AttributeValueEnumerationXmlDeserializer() {
        super(AttributeValueEnumeration.kmipTag, AttributeValueEnumeration.encodingType);
    }

    @Override
    protected AttributeValueEnumeration.AttributeValueEnumerationBuilder createBuilder() {
        return AttributeValueEnumeration.builder();
    }

    @Override
    protected void setValue(AttributeValueEnumeration.AttributeValueEnumerationBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        String name = ctxt.readValue(p, String.class);
        String attributeName = (String) ctxt.getAttribute("attributeName");
        KmipTag.Value enumType = KmipTag.fromName(StringUtils.covertTitleToPascalCase(attributeName));
        if (enumType == null) {
            ctxt.reportInputMismatch(handledType(), "Invalid attribute name for " + handledType().getSimpleName());
            return;
        }
        KmipEnumeration.Value<?> value = KmipEnumeration.getFromName(enumType).apply(name);
        builder.value(value);
    }

    @Override
    protected AttributeValueEnumeration build(AttributeValueEnumeration.AttributeValueEnumerationBuilder builder) {
        return builder.build();
    }
}