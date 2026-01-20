package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.Offset;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.ReKeyOpRequestPayload;

import java.io.IOException;

public class ReKeyOpRequestPayloadXmlDeserializer extends AbstractKmipStructureXmlDeserializer<ReKeyOpRequestPayload, ReKeyOpRequestPayload.ReKeyOpRequestPayloadBuilder> {

    public ReKeyOpRequestPayloadXmlDeserializer() {
        super(ReKeyOpRequestPayload.kmipTag);
    }

    @Override
    protected ReKeyOpRequestPayload.ReKeyOpRequestPayloadBuilder createBuilder() {
        return ReKeyOpRequestPayload.builder();
    }

    @Override
    protected void setValue(ReKeyOpRequestPayload.ReKeyOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.OFFSET -> builder.offset(ctxt.readValue(p, Offset.class));
            case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
                    builder.templateAttribute(ctxt.readValue(p, TemplateAttribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected ReKeyOpRequestPayload build(ReKeyOpRequestPayload.ReKeyOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}