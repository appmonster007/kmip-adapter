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
import org.purpleBean.kmip.common.enumeration.UnwrapMode;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for UnwrapMode.
 */
public class UnwrapModeXmlDeserializer extends KmipDataTypeXmlDeserializer<UnwrapMode> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.UNWRAP_MODE);

    @Override
    public UnwrapMode deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(UnwrapMode.class, "Expected XML element object for UnwrapMode");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(UnwrapMode.class, "Invalid Tag for UnwrapMode");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(UnwrapMode.class, "Missing or invalid '@type' attribute for UnwrapMode");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(UnwrapMode.class, "Missing or non-text '@value' attribute for UnwrapMode");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        UnwrapMode unwrapmode = new UnwrapMode(UnwrapMode.fromName(spec, description));
        if (!unwrapmode.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("UnwrapMode '%s' not supported for spec %s", description, spec));
        }

        return unwrapmode;
    }
}
