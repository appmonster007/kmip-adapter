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
import org.purpleBean.kmip.common.enumeration.Ephemeral;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for Ephemeral.
 */
public class EphemeralXmlDeserializer extends KmipDataTypeXmlDeserializer<Ephemeral> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.EPHEMERAL);

    @Override
    public Ephemeral deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(Ephemeral.class, "Expected XML element object for Ephemeral");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(Ephemeral.class, "Invalid Tag for Ephemeral");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(Ephemeral.class, "Missing or invalid '@type' attribute for Ephemeral");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(Ephemeral.class, "Missing or non-text '@value' attribute for Ephemeral");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        Ephemeral ephemeral = new Ephemeral(Ephemeral.fromName(spec, description));
        if (!ephemeral.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("Ephemeral '%s' not supported for spec %s", description, spec));
        }

        return ephemeral;
    }
}
