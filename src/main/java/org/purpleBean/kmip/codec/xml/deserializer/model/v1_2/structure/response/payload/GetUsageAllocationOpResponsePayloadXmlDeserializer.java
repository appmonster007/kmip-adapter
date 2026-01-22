package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.GetUsageAllocationOpResponsePayload;

import java.io.IOException;

public class GetUsageAllocationOpResponsePayloadXmlDeserializer extends AbstractKmipStructureXmlDeserializer<GetUsageAllocationOpResponsePayload, GetUsageAllocationOpResponsePayload.GetUsageAllocationOpResponsePayloadBuilder> {

    public GetUsageAllocationOpResponsePayloadXmlDeserializer() {
        super(GetUsageAllocationOpResponsePayload.kmipTag);
    }

    @Override
    protected GetUsageAllocationOpResponsePayload.GetUsageAllocationOpResponsePayloadBuilder createBuilder() {
        return GetUsageAllocationOpResponsePayload.builder();
    }

    @Override
    protected void setValue(GetUsageAllocationOpResponsePayload.GetUsageAllocationOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        if (nodeTag.equals(KmipTag.Standard.UNIQUE_IDENTIFIER)) {
            builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected GetUsageAllocationOpResponsePayload build(GetUsageAllocationOpResponsePayload.GetUsageAllocationOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
