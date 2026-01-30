package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.ExtensionType;

import java.io.IOException;

public class ExtensionTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ExtensionType, ExtensionType.ExtensionTypeBuilder> {

    public ExtensionTypeJsonDeserializer() {
        super(ExtensionType.kmipTag, ExtensionType.encodingType);
    }

    @Override
    protected ExtensionType.ExtensionTypeBuilder createBuilder() {
        return ExtensionType.builder();
    }

    @Override
    protected void setValue(ExtensionType.ExtensionTypeBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Integer.class));
    }

    @Override
    protected ExtensionType build(ExtensionType.ExtensionTypeBuilder builder) {
        return builder.build();
    }
}
