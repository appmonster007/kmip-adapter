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
import org.purpleBean.kmip.common.AsynchronousCorrelationValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AsynchronousCorrelationValueXmlDeserializer extends KmipDataTypeXmlDeserializer<AsynchronousCorrelationValue> {
    private final KmipTag kmipTag = AsynchronousCorrelationValue.kmipTag;
    private final EncodingType encodingType = AsynchronousCorrelationValue.encodingType;

    @Override
    public AsynchronousCorrelationValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(AsynchronousCorrelationValue.class, "Expected XML object for AsynchronousCorrelationValue");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(AsynchronousCorrelationValue.class, "Invalid Tag for AsynchronousCorrelationValue");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(AsynchronousCorrelationValue.class, "Missing or invalid '@type' attribute for AsynchronousCorrelationValue");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(AsynchronousCorrelationValue.class,
                    "Missing or non-text 'value' for AsynchronousCorrelationValue");
            return null;
        }

        ByteBuffer value = codec.treeToValue(valueNode, ByteBuffer.class);
        AsynchronousCorrelationValue asynchronousCorrelationValue = AsynchronousCorrelationValue.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!asynchronousCorrelationValue.isSupported()) {
            ctxt.reportInputMismatch(AsynchronousCorrelationValue.class, "AsynchronousCorrelationValue not supported for spec " + spec);
            return null;
        }

        return asynchronousCorrelationValue;
    }
}