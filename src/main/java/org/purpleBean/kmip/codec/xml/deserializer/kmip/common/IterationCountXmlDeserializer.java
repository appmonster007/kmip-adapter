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
import org.purpleBean.kmip.common.IterationCount;

import java.io.IOException;

public class IterationCountXmlDeserializer extends KmipDataTypeXmlDeserializer<IterationCount> {
    private final KmipTag kmipTag = IterationCount.kmipTag;
    private final EncodingType encodingType = IterationCount.encodingType;

    @Override
    public IterationCount deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(IterationCount.class, "Expected XML object for IterationCount");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(IterationCount.class, "Invalid Tag for IterationCount");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(IterationCount.class, "Missing or invalid '@type' attribute for IterationCount");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(IterationCount.class,
                    "Missing or non-number 'value' for IterationCount");
            return null;
        }

        int value = Integer.parseInt(valueNode.asText());
        IterationCount iterationCount = IterationCount.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!iterationCount.isSupported()) {
            ctxt.reportInputMismatch(IterationCount.class, "IterationCount not supported for spec " + spec);
            return null;
        }

        return iterationCount;
    }
}