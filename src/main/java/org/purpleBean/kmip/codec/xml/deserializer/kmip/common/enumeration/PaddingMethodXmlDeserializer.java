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
import org.purpleBean.kmip.common.enumeration.PaddingMethod;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for PaddingMethod.
 */
public class PaddingMethodXmlDeserializer extends KmipDataTypeXmlDeserializer<PaddingMethod> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.PADDING_METHOD);

    @Override
    public PaddingMethod deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(PaddingMethod.class, "Expected XML element object for PaddingMethod");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(PaddingMethod.class, "Invalid Tag for PaddingMethod");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(PaddingMethod.class, "Missing or invalid '@type' attribute for PaddingMethod");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(PaddingMethod.class, "Missing or non-text '@value' attribute for PaddingMethod");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        PaddingMethod paddingmethod = new PaddingMethod(PaddingMethod.fromName(spec, description));
        if (!paddingmethod.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("PaddingMethod '%s' not supported for spec %s", description, spec));
        }

        return paddingmethod;
    }
}
