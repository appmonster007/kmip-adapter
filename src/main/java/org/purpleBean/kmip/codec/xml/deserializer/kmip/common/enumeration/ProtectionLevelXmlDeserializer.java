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
import org.purpleBean.kmip.common.enumeration.ProtectionLevel;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for ProtectionLevel.
 */
public class ProtectionLevelXmlDeserializer extends KmipDataTypeXmlDeserializer<ProtectionLevel> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.PROTECTION_LEVEL);

    @Override
    public ProtectionLevel deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(ProtectionLevel.class, "Expected XML element object for ProtectionLevel");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(ProtectionLevel.class, "Invalid Tag for ProtectionLevel");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(ProtectionLevel.class, "Missing or invalid '@type' attribute for ProtectionLevel");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ProtectionLevel.class, "Missing or non-text '@value' attribute for ProtectionLevel");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        ProtectionLevel protectionlevel = new ProtectionLevel(ProtectionLevel.fromName(spec, description));
        if (!protectionlevel.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("ProtectionLevel '%s' not supported for spec %s", description, spec));
        }

        return protectionlevel;
    }
}
