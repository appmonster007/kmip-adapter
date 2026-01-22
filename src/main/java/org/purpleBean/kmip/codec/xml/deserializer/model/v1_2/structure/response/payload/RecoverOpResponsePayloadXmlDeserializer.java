package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.RecoverOpResponsePayload;

import java.io.IOException;

public class RecoverOpResponsePayloadXmlDeserializer extends AbstractKmipStructureXmlDeserializer<RecoverOpResponsePayload, RecoverOpResponsePayload.RecoverOpResponsePayloadBuilder> {

    public RecoverOpResponsePayloadXmlDeserializer() {
        super(RecoverOpResponsePayload.kmipTag);
    }

    @Override
    protected RecoverOpResponsePayload.RecoverOpResponsePayloadBuilder createBuilder() {
        return RecoverOpResponsePayload.builder();
    }

    @Override
    protected void setValue(RecoverOpResponsePayload.RecoverOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        if (nodeTag.equals(KmipTag.Standard.UNIQUE_IDENTIFIER)) {
            builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected RecoverOpResponsePayload build(RecoverOpResponsePayload.RecoverOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
