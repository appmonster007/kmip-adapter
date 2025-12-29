package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

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
import org.purpleBean.kmip.common.BatchCount;

import java.io.IOException;

public class BatchCountXmlDeserializer extends KmipDataTypeXmlDeserializer<BatchCount> {
    private final KmipTag kmipTag = BatchCount.kmipTag;
    private final EncodingType encodingType = BatchCount.encodingType;

    @Override
    public BatchCount deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(BatchCount.class, "Expected XML object for BatchCount");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(BatchCount.class, "Invalid Tag for BatchCount");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(BatchCount.class, "Missing or invalid '@type' attribute for BatchCount");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(BatchCount.class,
                    "Missing or non-text 'value' for BatchCount");
            return null;
        }

        BatchCount batchCount;
        try {
            int value = Integer.parseInt(valueNode.asText());
            if (value < 0) {
                ctxt.reportInputMismatch(BatchCount.class, "BatchCount value must be a non-negative integer");
                return null;
            }
            batchCount = BatchCount.builder().value(value).build();
        } catch (NumberFormatException e) {
            ctxt.reportInputMismatch(BatchCount.class, "Invalid integer value for BatchCount: " + valueNode.asText());
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();

        if (!batchCount.isSupported()) {
            ctxt.reportInputMismatch(BatchCount.class, "BatchCount not supported for spec " + spec);
            return null;
        }

        return batchCount;
    }
}