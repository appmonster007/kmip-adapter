package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure.response.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.response.payload.ExportOpResponsePayload;

import java.io.IOException;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

public class ExportOpResponsePayloadJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ExportOpResponsePayload, ExportOpResponsePayload.ExportOpResponsePayloadBuilder> {

    public ExportOpResponsePayloadJsonDeserializer() {
        super(ExportOpResponsePayload.kmipTag, ExportOpResponsePayload.encodingType);
    }

    @Override
    protected ExportOpResponsePayload.ExportOpResponsePayloadBuilder createBuilder() {
        return ExportOpResponsePayload.builder();
    }

    @Override
    protected void setValue(ExportOpResponsePayload.ExportOpResponsePayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(ctxt.readValue(p, ObjectType.class));
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            default -> {
                if (ManagedObject.isManagedObject(nodeTag)) {
                    builder.object(ctxt.readValue(p, ManagedObject.class));
                } else {
                    throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
                }
            }
        }
    }

    @Override
    protected ExportOpResponsePayload build(ExportOpResponsePayload.ExportOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}