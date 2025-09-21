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
import org.purpleBean.kmip.common.enumeration.AdjustmentType;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for AdjustmentType.
 */
public class AdjustmentTypeXmlDeserializer extends KmipDataTypeXmlDeserializer<AdjustmentType> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ADJUSTMENT_TYPE);

    @Override
    public AdjustmentType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(AdjustmentType.class, "Expected XML element object for AdjustmentType");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(AdjustmentType.class, "Invalid Tag for AdjustmentType");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(AdjustmentType.class, "Missing or invalid '@type' attribute for AdjustmentType");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(AdjustmentType.class, "Missing or non-text '@value' attribute for AdjustmentType");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        AdjustmentType adjustmenttype = new AdjustmentType(AdjustmentType.fromName(spec, description));
        if (!adjustmenttype.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("AdjustmentType '%s' not supported for spec %s", description, spec));
        }

        return adjustmenttype;
    }
}
