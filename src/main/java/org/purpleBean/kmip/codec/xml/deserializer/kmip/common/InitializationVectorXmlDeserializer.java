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
import org.purpleBean.kmip.common.InitializationVector;

import java.io.IOException;
import java.nio.ByteBuffer;

public class InitializationVectorXmlDeserializer extends KmipDataTypeXmlDeserializer<InitializationVector> {
    private final KmipTag kmipTag = InitializationVector.kmipTag;
    private final EncodingType encodingType = InitializationVector.encodingType;

    @Override
    public InitializationVector deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(InitializationVector.class, "Expected XML object for InitializationVector");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(InitializationVector.class, "Invalid Tag for InitializationVector");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(InitializationVector.class, "Missing or invalid '@type' attribute for InitializationVector");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(InitializationVector.class,
                    "Missing or non-text 'value' for InitializationVector");
            return null;
        }

        ByteBuffer value = codec.treeToValue(valueNode, ByteBuffer.class);
        InitializationVector initializationVector = InitializationVector.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!initializationVector.isSupported()) {
            ctxt.reportInputMismatch(InitializationVector.class, "InitializationVector not supported for spec " + spec);
            return null;
        }

        return initializationVector;
    }
}