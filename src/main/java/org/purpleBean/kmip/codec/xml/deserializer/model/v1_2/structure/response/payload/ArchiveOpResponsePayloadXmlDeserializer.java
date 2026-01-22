package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.ArchiveOpResponsePayload;

import java.io.IOException;

public class ArchiveOpResponsePayloadXmlDeserializer extends AbstractKmipStructureXmlDeserializer<ArchiveOpResponsePayload, ArchiveOpResponsePayload.ArchiveOpResponsePayloadBuilder> {

    public ArchiveOpResponsePayloadXmlDeserializer() {
        super(ArchiveOpResponsePayload.kmipTag);
    }

    @Override
    protected ArchiveOpResponsePayload.ArchiveOpResponsePayloadBuilder createBuilder() {
        return ArchiveOpResponsePayload.builder();
    }

    @Override
    protected void setValue(ArchiveOpResponsePayload.ArchiveOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        if (nodeTag.equals(KmipTag.Standard.UNIQUE_IDENTIFIER)) {
            builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ArchiveOpResponsePayload build(ArchiveOpResponsePayload.ArchiveOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
