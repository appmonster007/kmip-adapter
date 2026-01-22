package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.DestroyOpResponsePayload;

import java.io.IOException;

public class DestroyOpResponsePayloadXmlDeserializer extends AbstractKmipStructureXmlDeserializer<DestroyOpResponsePayload, DestroyOpResponsePayload.DestroyOpResponsePayloadBuilder> {

    public DestroyOpResponsePayloadXmlDeserializer() {
        super(DestroyOpResponsePayload.kmipTag);
    }

    @Override
    protected DestroyOpResponsePayload.DestroyOpResponsePayloadBuilder createBuilder() {
        return DestroyOpResponsePayload.builder();
    }

    @Override
    protected void setValue(DestroyOpResponsePayload.DestroyOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        if (nodeTag.equals(KmipTag.Standard.UNIQUE_IDENTIFIER)) {
            builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected DestroyOpResponsePayload build(DestroyOpResponsePayload.DestroyOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
