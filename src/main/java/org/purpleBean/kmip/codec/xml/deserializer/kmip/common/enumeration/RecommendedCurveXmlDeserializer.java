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
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for RecommendedCurve.
 */
public class RecommendedCurveXmlDeserializer extends KmipDataTypeXmlDeserializer<RecommendedCurve> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.RECOMMENDED_CURVE);

    @Override
    public RecommendedCurve deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(RecommendedCurve.class, "Expected XML element object for RecommendedCurve");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(RecommendedCurve.class, "Invalid Tag for RecommendedCurve");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(RecommendedCurve.class, "Missing or invalid '@type' attribute for RecommendedCurve");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(RecommendedCurve.class, "Missing or non-text '@value' attribute for RecommendedCurve");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        RecommendedCurve recommendedcurve = new RecommendedCurve(RecommendedCurve.fromName(spec, description));
        if (!recommendedcurve.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("RecommendedCurve '%s' not supported for spec %s", description, spec));
        }

        return recommendedcurve;
    }
}
