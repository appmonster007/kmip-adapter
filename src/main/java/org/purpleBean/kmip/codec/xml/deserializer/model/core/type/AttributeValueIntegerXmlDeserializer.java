package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipMaskType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueInteger;
import org.purpleBean.kmip.util.StringUtils;

import java.io.IOException;
import java.util.function.Function;

public class AttributeValueIntegerXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AttributeValueInteger, AttributeValueInteger.AttributeValueIntegerBuilder> {

    public AttributeValueIntegerXmlDeserializer() {
        super(AttributeValueInteger.kmipTag, AttributeValueInteger.encodingType);
    }

    @Override
    protected AttributeValueInteger.AttributeValueIntegerBuilder createBuilder() {
        return AttributeValueInteger.builder();
    }

    @Override
    protected void setValue(AttributeValueInteger.AttributeValueIntegerBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = ctxt.readValue(p, String.class);
        String attributeName = (String) ctxt.getAttribute("attributeName");
        if (attributeName != null && attributeName.toLowerCase().contains("mask")) {
            KmipTag.Value kmipTag = KmipTag.fromName(StringUtils.covertTitleToPascalCase(attributeName));
            Function<String, ? extends KmipMaskType> fromMaskString = KmipMaskType.getFromMaskString(kmipTag);
            builder.value(fromMaskString.apply(value).getValue())
                    .maskString(value);
        } else {
            builder.value(Integer.parseInt(value));
        }
    }

    @Override
    protected AttributeValueInteger build(AttributeValueInteger.AttributeValueIntegerBuilder builder) {
        return builder.build();
    }
}