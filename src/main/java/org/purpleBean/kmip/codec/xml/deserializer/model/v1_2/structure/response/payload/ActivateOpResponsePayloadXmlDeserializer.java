package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.ActivateOpResponsePayload;

import java.io.IOException;

public class ActivateOpResponsePayloadXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ActivateOpResponsePayload, ActivateOpResponsePayload.ActivateOpResponsePayloadBuilder> {

    public ActivateOpResponsePayloadXmlDeserializer() {
        super(ActivateOpResponsePayload.kmipTag, ActivateOpResponsePayload.encodingType);
    }

    @Override
    protected ActivateOpResponsePayload.ActivateOpResponsePayloadBuilder createBuilder() {
        return ActivateOpResponsePayload.builder();
    }

    @Override
    protected void setValue(ActivateOpResponsePayload.ActivateOpResponsePayloadBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        if (nodeTag.equals(KmipTag.Standard.UNIQUE_IDENTIFIER)) {
            builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ActivateOpResponsePayload build(ActivateOpResponsePayload.ActivateOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
