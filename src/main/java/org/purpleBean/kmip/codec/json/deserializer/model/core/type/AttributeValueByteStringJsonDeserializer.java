package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeValueByteString;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AttributeValueByteStringJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AttributeValueByteString, AttributeValueByteString.AttributeValueByteStringBuilder> {

    public AttributeValueByteStringJsonDeserializer() {
        super(AttributeValueByteString.kmipTag, AttributeValueByteString.encodingType);
    }

    @Override
    protected AttributeValueByteString.AttributeValueByteStringBuilder createBuilder() {
        return AttributeValueByteString.builder();
    }

    @Override
    protected void setValue(AttributeValueByteString.AttributeValueByteStringBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, ByteBuffer.class));
    }

    @Override
    protected AttributeValueByteString build(AttributeValueByteString.AttributeValueByteStringBuilder builder) {
        return builder.build();
    }
}
