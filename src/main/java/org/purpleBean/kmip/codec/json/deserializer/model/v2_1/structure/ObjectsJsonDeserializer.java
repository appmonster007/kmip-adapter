package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure;

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
import org.purpleBean.kmip.model.v2_1.structure.Objects;

import java.io.IOException;

public class ObjectsJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<Objects, Objects.ObjectsBuilder> {

    public ObjectsJsonDeserializer() {
        super(Objects.kmipTag, Objects.encodingType);
    }

    @Override
    protected Objects.ObjectsBuilder createBuilder() {
        return Objects.builder();
    }

    @Override
    protected void setValue(Objects.ObjectsBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
                KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(ctxt.readValue(p, org.purpleBean.kmip.model.core.type.UniqueIdentifier.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected Objects build(Objects.ObjectsBuilder builder) {
        return builder.build();
    }
}