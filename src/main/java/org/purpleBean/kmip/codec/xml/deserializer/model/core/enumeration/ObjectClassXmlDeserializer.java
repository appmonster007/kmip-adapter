package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectClass;

import java.io.IOException;

public class ObjectClassXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ObjectClass, ObjectClass.ObjectClassBuilder> {

    public ObjectClassXmlDeserializer() {
        super(ObjectClass.kmipTag, ObjectClass.encodingType);
    }

    @Override
    protected ObjectClass.ObjectClassBuilder createBuilder() {
        return ObjectClass.builder();
    }

    @Override
    protected void setValue(ObjectClass.ObjectClassBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ObjectClass.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected ObjectClass build(ObjectClass.ObjectClassBuilder builder) {
        return builder.build();
    }
}