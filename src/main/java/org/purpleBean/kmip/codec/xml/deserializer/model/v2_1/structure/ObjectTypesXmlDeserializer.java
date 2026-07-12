package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.structure.ObjectTypes;

import java.io.IOException;

public class ObjectTypesXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ObjectTypes, ObjectTypes.ObjectTypesBuilder> {

    public ObjectTypesXmlDeserializer() {
        super(ObjectTypes.kmipTag, ObjectTypes.encodingType);
    }

    @Override
    protected ObjectTypes.ObjectTypesBuilder createBuilder() {
        return ObjectTypes.builder();
    }

    @Override
    protected void setValue(ObjectTypes.ObjectTypesBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(ctxt.readValue(p, ObjectType.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ObjectTypes build(ObjectTypes.ObjectTypesBuilder builder) {
        return builder.build();
    }
}