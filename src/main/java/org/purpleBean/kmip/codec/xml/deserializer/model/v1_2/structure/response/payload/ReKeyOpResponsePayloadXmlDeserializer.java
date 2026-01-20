package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.ReKeyOpResponsePayload;

import java.io.IOException;

public class ReKeyOpResponsePayloadXmlDeserializer extends AbstractKmipStructureXmlDeserializer<ReKeyOpResponsePayload, ReKeyOpResponsePayload.ReKeyOpResponsePayloadBuilder> {

    public ReKeyOpResponsePayloadXmlDeserializer() {
        super(ReKeyOpResponsePayload.kmipTag);
    }

    @Override
    protected ReKeyOpResponsePayload.ReKeyOpResponsePayloadBuilder createBuilder() {
        return ReKeyOpResponsePayload.builder();
    }

    @Override
    protected void setValue(ReKeyOpResponsePayload.ReKeyOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
                    builder.templateAttribute(ctxt.readValue(p, TemplateAttribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ReKeyOpResponsePayload build(ReKeyOpResponsePayload.ReKeyOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}