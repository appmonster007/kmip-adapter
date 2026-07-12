package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.StorageStatusMask;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.LocateOpRequestPayload;

import java.io.IOException;

public class LocateOpRequestPayloadJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<LocateOpRequestPayload, LocateOpRequestPayload.LocateOpRequestPayloadBuilder> {

    public LocateOpRequestPayloadJsonDeserializer() {
        super(LocateOpRequestPayload.kmipTag, LocateOpRequestPayload.encodingType);
    }

    @Override
    protected LocateOpRequestPayload.LocateOpRequestPayloadBuilder createBuilder() {
        return LocateOpRequestPayload.builder();
    }

    @Override
    protected void setValue(LocateOpRequestPayload.LocateOpRequestPayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.STORAGE_STATUS_MASK ->
                    builder.storageStatusMask(ctxt.readValue(p, StorageStatusMask.class));
            case KmipTag.Standard.ATTRIBUTES ->
                    builder.attributes(ctxt.readValue(p, Attributes.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected LocateOpRequestPayload build(LocateOpRequestPayload.LocateOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
