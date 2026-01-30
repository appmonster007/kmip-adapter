package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueTextString;

import java.io.IOException;

public class AttributeValueTextStringJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AttributeValueTextString, AttributeValueTextString.AttributeValueTextStringBuilder> {

    public AttributeValueTextStringJsonDeserializer() {
        super(AttributeValueTextString.kmipTag, AttributeValueTextString.encodingType);
    }

    @Override
    protected AttributeValueTextString.AttributeValueTextStringBuilder createBuilder() {
        return AttributeValueTextString.builder();
    }

    @Override
    protected void setValue(AttributeValueTextString.AttributeValueTextStringBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
    }

    @Override
    protected AttributeValueTextString build(AttributeValueTextString.AttributeValueTextStringBuilder builder) {
        return builder.build();
    }
}
