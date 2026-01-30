package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;

import java.io.IOException;

public class ObjectTypeJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ObjectType, ObjectType.ObjectTypeBuilder> {

    public ObjectTypeJsonDeserializer() {
        super(ObjectType.kmipTag, ObjectType.encodingType);
    }

    @Override
    protected ObjectType.ObjectTypeBuilder createBuilder() {
        return ObjectType.builder();
    }

    @Override
    protected void setValue(ObjectType.ObjectTypeBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ObjectType.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected ObjectType build(ObjectType.ObjectTypeBuilder builder) {
        return builder.build();
    }
}
