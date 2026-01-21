package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.LocateOpResponsePayload;

import java.io.IOException;

public class LocateOpResponsePayloadXmlDeserializer extends AbstractKmipStructureXmlDeserializer<LocateOpResponsePayload, LocateOpResponsePayload.LocateOpResponsePayloadBuilder> {

    public LocateOpResponsePayloadXmlDeserializer() {
        super(LocateOpResponsePayload.kmipTag);
    }

    @Override
    protected LocateOpResponsePayload.LocateOpResponsePayloadBuilder createBuilder() {
        return LocateOpResponsePayload.builder();
    }

    @Override
    protected void setValue(LocateOpResponsePayload.LocateOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected LocateOpResponsePayload build(LocateOpResponsePayload.LocateOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
