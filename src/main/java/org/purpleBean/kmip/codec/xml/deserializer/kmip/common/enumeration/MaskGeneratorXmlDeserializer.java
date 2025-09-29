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
import org.purpleBean.kmip.common.enumeration.MaskGenerator;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for MaskGenerator.
 */
public class MaskGeneratorXmlDeserializer extends KmipDataTypeXmlDeserializer<MaskGenerator> {
    private final KmipTag kmipTag = MaskGenerator.kmipTag;
    private final EncodingType encodingType = MaskGenerator.encodingType;

    @Override
    public MaskGenerator deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(MaskGenerator.class, "Expected XML element object for MaskGenerator");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(MaskGenerator.class, "Invalid Tag for MaskGenerator");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(MaskGenerator.class, "Missing or invalid '@type' attribute for MaskGenerator");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(MaskGenerator.class, "Missing or non-text '@value' attribute for MaskGenerator");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        MaskGenerator maskgenerator = new MaskGenerator(MaskGenerator.fromName(description));
        if (!maskgenerator.isSupported()) {
            throw new NoSuchElementException(
                String.format("MaskGenerator '%s' not supported for spec %s", description, spec));
        }

        return maskgenerator;
    }
}
