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
import org.purpleBean.kmip.common.enumeration.BatchErrorContinuationOption;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for BatchErrorContinuationOption.
 */
public class BatchErrorContinuationOptionXmlDeserializer extends KmipDataTypeXmlDeserializer<BatchErrorContinuationOption> {
    private final KmipTag kmipTag = BatchErrorContinuationOption.kmipTag;
    private final EncodingType encodingType = BatchErrorContinuationOption.encodingType;

    @Override
    public BatchErrorContinuationOption deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(BatchErrorContinuationOption.class, "Expected XML element object for BatchErrorContinuationOption");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(BatchErrorContinuationOption.class, "Invalid Tag for BatchErrorContinuationOption");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(BatchErrorContinuationOption.class, "Missing or invalid '@type' attribute for BatchErrorContinuationOption");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(BatchErrorContinuationOption.class, "Missing or non-text '@value' attribute for BatchErrorContinuationOption");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        BatchErrorContinuationOption batcherrorcontinuationoption = new BatchErrorContinuationOption(BatchErrorContinuationOption.fromName(description));
        if (!batcherrorcontinuationoption.isSupported()) {
            throw new NoSuchElementException(
                String.format("BatchErrorContinuationOption '%s' not supported for spec %s", description, spec));
        }

        return batcherrorcontinuationoption;
    }
}
