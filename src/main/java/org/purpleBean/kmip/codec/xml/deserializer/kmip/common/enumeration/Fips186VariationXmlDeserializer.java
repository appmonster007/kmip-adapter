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
import org.purpleBean.kmip.common.enumeration.Fips186Variation;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for Fips186Variation.
 */
public class Fips186VariationXmlDeserializer extends KmipDataTypeXmlDeserializer<Fips186Variation> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.FIPS186_VARIATION);

    @Override
    public Fips186Variation deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(Fips186Variation.class, "Expected XML element object for Fips186Variation");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(Fips186Variation.class, "Invalid Tag for Fips186Variation");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(Fips186Variation.class, "Missing or invalid '@type' attribute for Fips186Variation");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(Fips186Variation.class, "Missing or non-text '@value' attribute for Fips186Variation");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        Fips186Variation fips186variation = new Fips186Variation(Fips186Variation.fromName(spec, description));
        if (!fips186variation.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("Fips186Variation '%s' not supported for spec %s", description, spec));
        }

        return fips186variation;
    }
}
