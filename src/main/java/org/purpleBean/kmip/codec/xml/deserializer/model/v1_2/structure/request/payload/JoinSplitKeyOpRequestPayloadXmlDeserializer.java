package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.enumeration.SecretDataType;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.JoinSplitKeyOpRequestPayload;

import java.io.IOException;

public class JoinSplitKeyOpRequestPayloadXmlDeserializer extends AbstractKmipStructureXmlDeserializer<JoinSplitKeyOpRequestPayload, JoinSplitKeyOpRequestPayload.JoinSplitKeyOpRequestPayloadBuilder> {

    public JoinSplitKeyOpRequestPayloadXmlDeserializer() {
        super(JoinSplitKeyOpRequestPayload.kmipTag);
    }

    @Override
    protected JoinSplitKeyOpRequestPayload.JoinSplitKeyOpRequestPayloadBuilder createBuilder() {
        return JoinSplitKeyOpRequestPayload.builder();
    }

    @Override
    protected void setValue(JoinSplitKeyOpRequestPayload.JoinSplitKeyOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(ctxt.readValue(p, ObjectType.class));
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.SECRET_DATA_TYPE -> builder.secretDataType(ctxt.readValue(p, SecretDataType.class));
            case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
                    builder.templateAttribute(ctxt.readValue(p, TemplateAttribute.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected JoinSplitKeyOpRequestPayload build(JoinSplitKeyOpRequestPayload.JoinSplitKeyOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
