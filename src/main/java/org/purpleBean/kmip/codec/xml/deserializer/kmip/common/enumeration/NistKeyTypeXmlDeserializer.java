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
import org.purpleBean.kmip.common.enumeration.NistKeyType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for NistKeyType.
 */
public class NistKeyTypeXmlDeserializer extends KmipDataTypeXmlDeserializer<NistKeyType> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.NIST_KEY_TYPE);

    @Override
    public NistKeyType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(NistKeyType.class, "Expected XML element object for NistKeyType");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(NistKeyType.class, "Invalid Tag for NistKeyType");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(NistKeyType.class, "Missing or invalid '@type' attribute for NistKeyType");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(NistKeyType.class, "Missing or non-text '@value' attribute for NistKeyType");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        NistKeyType nistkeytype = new NistKeyType(NistKeyType.fromName(spec, description));
        if (!nistkeytype.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("NistKeyType '%s' not supported for spec %s", description, spec));
        }

        return nistkeytype;
    }
}
