package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

import java.io.IOException;

public class UniqueIdentifierXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<UniqueIdentifier, UniqueIdentifier.UniqueIdentifierBuilder> {

    public UniqueIdentifierXmlDeserializer() {
        super(UniqueIdentifier.kmipTag, UniqueIdentifier.encodingType);
    }

    @Override
    protected UniqueIdentifier.UniqueIdentifierBuilder createBuilder() {
        return UniqueIdentifier.builder();
    }

    @Override
    protected String getType(JsonNode node, DeserializationContext ctxt, UniqueIdentifier.UniqueIdentifierBuilder builder) throws IOException {
        // In KMIP 2.1+, UniqueIdentifier may appear as TextString, Enumeration (e.g. "IDPlaceholder"),
        // or Integer (batch item index). All three encode as a String internally.
        JsonNode typeNode = node.get("type");
        if (typeNode != null && typeNode.isTextual()) {
            String type = typeNode.asText();
            if (EncodingType.TEXT_STRING.getDescription().equals(type)
                    || EncodingType.ENUMERATION.getDescription().equals(type)
                    || EncodingType.INTEGER.getDescription().equals(type)) {
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