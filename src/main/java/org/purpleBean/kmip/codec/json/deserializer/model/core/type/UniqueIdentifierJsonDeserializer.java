package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

import java.io.IOException;

public class UniqueIdentifierJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<UniqueIdentifier, UniqueIdentifier.UniqueIdentifierBuilder> {

    public UniqueIdentifierJsonDeserializer() {
        super(UniqueIdentifier.kmipTag, UniqueIdentifier.encodingType);
    }

    @Override
    protected UniqueIdentifier.UniqueIdentifierBuilder createBuilder() {
        return UniqueIdentifier.builder();
    }

    @Override
    protected String getType(JsonNode node, DeserializationContext ctxt, UniqueIdentifier.UniqueIdentifierBuilder builder) throws IOException {
        // See UniqueIdentifierXmlDeserializer for the rationale behind this whitelist.
        JsonNode typeNode = node.get("type");
        if (typeNode != null && typeNode.isTextual()) {
            String type = typeNode.asText();
            if (EncodingType.TEXT_STRING.getDescription().equals(type)
                    || EncodingType.ENUMERATION.getDescription().equals(type)
                    || EncodingType.INTEGER.getDescription().equals(type)
                    || EncodingType.IDENTIFIER.getDescription().equals(type)
                    || EncodingType.REFERENCE.getDescription().equals(type)
                    || EncodingType.NAME_REFERENCE.getDescription().equals(type)) {
                return type;
            }
        }
        return super.getType(node, ctxt, builder);
    }

    @Override
    protected void setValue(UniqueIdentifier.UniqueIdentifierBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, String.class));
        EncodingType.fromName(type).ifPresent(builder::sourceEncoding);
    }

    @Override
    protected UniqueIdentifier build(UniqueIdentifier.UniqueIdentifierBuilder builder) {
        return builder.build();
    }
}
