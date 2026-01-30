package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.OpaqueDataValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class OpaqueDataValueJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<OpaqueDataValue, OpaqueDataValue.OpaqueDataValueBuilder> {

    public OpaqueDataValueJsonDeserializer() {
        super(OpaqueDataValue.kmipTag, OpaqueDataValue.encodingType);
    }

    @Override
    protected OpaqueDataValue.OpaqueDataValueBuilder createBuilder() {
        return OpaqueDataValue.builder();
    }

    @Override
    protected void setValue(OpaqueDataValue.OpaqueDataValueBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, ByteBuffer.class));
    }

    @Override
    protected OpaqueDataValue build(OpaqueDataValue.OpaqueDataValueBuilder builder) {
        return builder.build();
    }
}
