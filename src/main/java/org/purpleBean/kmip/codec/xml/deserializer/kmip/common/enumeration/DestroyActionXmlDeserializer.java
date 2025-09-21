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
import org.purpleBean.kmip.common.enumeration.DestroyAction;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for DestroyAction.
 */
public class DestroyActionXmlDeserializer extends KmipDataTypeXmlDeserializer<DestroyAction> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.DESTROY_ACTION);

    @Override
    public DestroyAction deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(DestroyAction.class, "Expected XML element object for DestroyAction");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(DestroyAction.class, "Invalid Tag for DestroyAction");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(DestroyAction.class, "Missing or invalid '@type' attribute for DestroyAction");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(DestroyAction.class, "Missing or non-text '@value' attribute for DestroyAction");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        DestroyAction destroyaction = new DestroyAction(DestroyAction.fromName(spec, description));
        if (!destroyaction.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("DestroyAction '%s' not supported for spec %s", description, spec));
        }

        return destroyaction;
    }
}
