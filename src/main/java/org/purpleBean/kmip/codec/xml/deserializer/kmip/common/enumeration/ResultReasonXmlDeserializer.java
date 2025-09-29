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
import org.purpleBean.kmip.common.enumeration.ResultReason;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for ResultReason.
 */
public class ResultReasonXmlDeserializer extends KmipDataTypeXmlDeserializer<ResultReason> {
    private final KmipTag kmipTag = ResultReason.kmipTag;
    private final EncodingType encodingType = ResultReason.encodingType;

    @Override
    public ResultReason deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(ResultReason.class, "Expected XML element object for ResultReason");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(ResultReason.class, "Invalid Tag for ResultReason");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(ResultReason.class, "Missing or invalid '@type' attribute for ResultReason");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ResultReason.class, "Missing or non-text '@value' attribute for ResultReason");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        ResultReason resultreason = new ResultReason(ResultReason.fromName(description));
        if (!resultreason.isSupported()) {
            throw new NoSuchElementException(
                String.format("ResultReason '%s' not supported for spec %s", description, spec));
        }

        return resultreason;
    }
}
