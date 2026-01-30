package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.OpaqueDataType;

import java.io.IOException;

public class OpaqueDataTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<OpaqueDataType, OpaqueDataType.OpaqueDataTypeBuilder> {

    public OpaqueDataTypeJsonDeserializer() {
        super(OpaqueDataType.kmipTag, OpaqueDataType.encodingType);
    }

    @Override
    protected OpaqueDataType.OpaqueDataTypeBuilder createBuilder() {
        return OpaqueDataType.builder();
    }

    @Override
    protected void setValue(OpaqueDataType.OpaqueDataTypeBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(OpaqueDataType.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected OpaqueDataType build(OpaqueDataType.OpaqueDataTypeBuilder builder) {
        return builder.build();
    }
}
