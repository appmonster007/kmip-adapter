package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

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
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.ObjectDefaults;

import java.io.IOException;

public class ObjectDefaultsJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ObjectDefaults, ObjectDefaults.ObjectDefaultsBuilder> {

    public ObjectDefaultsJsonDeserializer() {
        super(ObjectDefaults.kmipTag, ObjectDefaults.encodingType);
    }

    @Override
    protected ObjectDefaults.ObjectDefaultsBuilder createBuilder() {
        return ObjectDefaults.builder();
    }

    @Override
    protected void setValue(ObjectDefaults.ObjectDefaultsBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(ctxt.readValue(p, ObjectType.class));
            case KmipTag.Standard.ATTRIBUTES -> builder.attributes(ctxt.readValue(p, Attributes.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ObjectDefaults build(ObjectDefaults.ObjectDefaultsBuilder builder) {
        return builder.build();
    }
}