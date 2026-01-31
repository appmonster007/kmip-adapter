package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;

import java.io.IOException;

public class ObjectTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ObjectType, ObjectType.ObjectTypeBuilder> {

    public ObjectTypeXmlDeserializer() {
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