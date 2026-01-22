package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.response.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.GetAttributeListOpResponsePayload;

import java.io.IOException;

public class GetAttributeListOpResponsePayloadXmlDeserializer extends AbstractKmipStructureXmlDeserializer<GetAttributeListOpResponsePayload, GetAttributeListOpResponsePayload.GetAttributeListOpResponsePayloadBuilder> {

    public GetAttributeListOpResponsePayloadXmlDeserializer() {
        super(GetAttributeListOpResponsePayload.kmipTag);
    }

    @Override
    protected GetAttributeListOpResponsePayload.GetAttributeListOpResponsePayloadBuilder createBuilder() {
        return GetAttributeListOpResponsePayload.builder();
    }

    @Override
    protected void setValue(GetAttributeListOpResponsePayload.GetAttributeListOpResponsePayloadBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.ATTRIBUTE_NAME -> builder.attributeName(ctxt.readValue(p, AttributeName.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected GetAttributeListOpResponsePayload build(GetAttributeListOpResponsePayload.GetAttributeListOpResponsePayloadBuilder builder) {
        return builder.build();
    }
}
