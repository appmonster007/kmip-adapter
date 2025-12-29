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
import org.purpleBean.kmip.common.AsynchronousIndicator;

import java.io.IOException;

public class AsynchronousIndicatorXmlDeserializer extends KmipDataTypeXmlDeserializer<AsynchronousIndicator> {
    private final KmipTag kmipTag = AsynchronousIndicator.kmipTag;
    private final EncodingType encodingType = AsynchronousIndicator.encodingType;

    @Override
    public AsynchronousIndicator deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(AsynchronousIndicator.class, "Expected XML object for AsynchronousIndicator");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(AsynchronousIndicator.class, "Invalid Tag for AsynchronousIndicator");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(AsynchronousIndicator.class, "Missing or invalid '@type' attribute for AsynchronousIndicator");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(AsynchronousIndicator.class,
                    "Missing or non-boolean 'value' for AsynchronousIndicator");
            return null;
        }

        boolean value = Boolean.parseBoolean(valueNode.asText());
        AsynchronousIndicator asynchronousIndicator = AsynchronousIndicator.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!asynchronousIndicator.isSupported()) {
            ctxt.reportInputMismatch(AsynchronousIndicator.class, "AsynchronousIndicator not supported for spec " + spec);
            return null;
        }

        return asynchronousIndicator;
    }
}