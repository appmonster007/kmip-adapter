package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure.request.payload;

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
import org.purpleBean.kmip.model.v2_1.structure.request.payload.RegisterOpRequestPayload;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;

import java.io.IOException;

public class RegisterOpRequestPayloadJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<RegisterOpRequestPayload, RegisterOpRequestPayload.RegisterOpRequestPayloadBuilder> {

    public RegisterOpRequestPayloadJsonDeserializer() {
        super(RegisterOpRequestPayload.kmipTag, RegisterOpRequestPayload.encodingType);
    }

    @Override
    protected RegisterOpRequestPayload.RegisterOpRequestPayloadBuilder createBuilder() {
        return RegisterOpRequestPayload.builder();
    }

    @Override
    protected void setValue(RegisterOpRequestPayload.RegisterOpRequestPayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.OBJECT_TYPE -> {
                ObjectType objectType = ctxt.readValue(p, ObjectType.class);
                ctxt.setAttribute("objectType", objectType.getDescription());
                builder.objectType(objectType);
            }
            case KmipTag.Standard.ATTRIBUTES -> builder.attributes(ctxt.readValue(p, Attributes.class));
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
    protected RegisterOpRequestPayload build(RegisterOpRequestPayload.RegisterOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}