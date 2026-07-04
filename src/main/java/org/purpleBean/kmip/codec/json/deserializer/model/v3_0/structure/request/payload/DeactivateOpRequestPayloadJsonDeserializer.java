package org.purpleBean.kmip.codec.json.deserializer.model.v3_0.structure.request.payload;

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
import org.purpleBean.kmip.model.core.type.DeactivationDate;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3_0.structure.DeactivationReason;
import org.purpleBean.kmip.model.v3_0.structure.request.payload.DeactivateOpRequestPayload;

import java.io.IOException;

public class DeactivateOpRequestPayloadJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<DeactivateOpRequestPayload, DeactivateOpRequestPayload.DeactivateOpRequestPayloadBuilder> {

    public DeactivateOpRequestPayloadJsonDeserializer() {
        super(DeactivateOpRequestPayload.kmipTag, DeactivateOpRequestPayload.encodingType);
    }

    @Override
    protected DeactivateOpRequestPayload.DeactivateOpRequestPayloadBuilder createBuilder() {
        return DeactivateOpRequestPayload.builder();
    }

    @Override
    protected void setValue(DeactivateOpRequestPayload.DeactivateOpRequestPayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER -> builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.DEACTIVATION_REASON -> builder.deactivationReason(ctxt.readValue(p, DeactivationReason.class));
            case KmipTag.Standard.DEACTIVATION_DATE -> builder.deactivationDate(ctxt.readValue(p, DeactivationDate.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected DeactivateOpRequestPayload build(DeactivateOpRequestPayload.DeactivateOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}