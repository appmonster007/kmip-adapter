package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.NistKeyType;

import java.io.IOException;

public class NistKeyTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<NistKeyType, NistKeyType.NistKeyTypeBuilder> {

    public NistKeyTypeJsonDeserializer() {
        super(NistKeyType.kmipTag, NistKeyType.encodingType);
    }

    @Override
    protected NistKeyType.NistKeyTypeBuilder createBuilder() {
        return NistKeyType.builder();
    }

    @Override
    protected void setValue(NistKeyType.NistKeyTypeBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(NistKeyType.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected NistKeyType build(NistKeyType.NistKeyTypeBuilder builder) {
        return builder.build();
    }
}
