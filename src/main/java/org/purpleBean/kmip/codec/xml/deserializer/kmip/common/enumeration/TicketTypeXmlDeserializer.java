package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.TicketType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for TicketType.
 */
public class TicketTypeXmlDeserializer extends KmipDataTypeXmlDeserializer<TicketType> {
    private final KmipTag kmipTag = TicketType.kmipTag;
    private final EncodingType encodingType = TicketType.encodingType;

    @Override
    public TicketType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(TicketType.class, "Expected XML element object for TicketType");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(TicketType.class, "Invalid Tag for TicketType");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(TicketType.class, "Missing or invalid '@type' attribute for TicketType");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(TicketType.class, "Missing or non-text '@value' attribute for TicketType");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        TicketType tickettype = new TicketType(TicketType.fromName(description));
        if (!tickettype.isSupported()) {
            throw new NoSuchElementException(
                String.format("TicketType '%s' not supported for spec %s", description, spec));
        }

        return tickettype;
    }
}
