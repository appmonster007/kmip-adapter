package org.purpleBean.kmip.codec.json.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.type.CryptographicUsageMask;
import org.purpleBean.kmip.model.core.type.LeaseTime;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.core.type.UsageLimitsCount;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.CheckOpResponsePayload;

import java.io.IOException;

public class CheckOpResponsePayloadJsonDeserializer extends AbstractKmipStructureJsonDeserializer<CheckOpResponsePayload, CheckOpResponsePayload.CheckOpResponsePayloadBuilder> {

    public CheckOpResponsePayloadJsonDeserializer() {
        super(CheckOpResponsePayload.kmipTag, CheckOpResponsePayload.encodingType);
    }

    @Override
    protected CheckOpResponsePayload.CheckOpResponsePayloadBuilder createBuilder() {
        return CheckOpResponsePayload.builder();
    }

    @Override
    protected void setValue(CheckOpResponsePayload.CheckOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.USAGE_LIMITS_COUNT ->
                    builder.usageLimitsCount(ctxt.readValue(p, UsageLimitsCount.class));
            case KmipTag.Standard.CRYPTOGRAPHIC_USAGE_MASK ->
                    builder.cryptographicUsageMask(ctxt.readValue(p, CryptographicUsageMask.class));
            case KmipTag.Standard.LEASE_TIME -> builder.leaseTime(ctxt.readValue(p, LeaseTime.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CheckOpResponsePayload build(CheckOpResponsePayload.CheckOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
