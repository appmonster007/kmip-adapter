package org.purpleBean.kmip.codec.json.deserializer.model.v3_0.structure.response.payload;

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
import org.purpleBean.kmip.model.v3_0.structure.response.payload.ObliterateOpResponsePayload;

import java.io.IOException;

public class ObliterateOpResponsePayloadJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<ObliterateOpResponsePayload, ObliterateOpResponsePayload.ObliterateOpResponsePayloadBuilder> {

    public ObliterateOpResponsePayloadJsonDeserializer() {
        super(ObliterateOpResponsePayload.kmipTag, ObliterateOpResponsePayload.encodingType);
    }

    @Override
    protected ObliterateOpResponsePayload.ObliterateOpResponsePayloadBuilder createBuilder() {
        return ObliterateOpResponsePayload.builder();
    }

    @Override
    protected void setValue(ObliterateOpResponsePayload.ObliterateOpResponsePayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        // TODO: Implement setting values on the builder based on the tag
        // KmipTag.Value nodeTag = KmipTag.fromName(tag);
        // switch (nodeTag) {
        //     case KmipTag.Standard.FIELD_1 -> builder.field1(ctxt.readValue(p, Field1.class));
        //     case KmipTag.Standard.FIELD_2 -> builder.field2(ctxt.readValue(p, Field2.class));
        //     default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        // }
    }

    @Override
    protected ObliterateOpResponsePayload build(ObliterateOpResponsePayload.ObliterateOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}