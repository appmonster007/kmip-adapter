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
import org.purpleBean.kmip.common.DigestValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DigestValueXmlDeserializer extends KmipDataTypeXmlDeserializer<DigestValue> {
    private final KmipTag kmipTag = DigestValue.kmipTag;
    private final EncodingType encodingType = DigestValue.encodingType;

    @Override
    public DigestValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(DigestValue.class, "Expected XML object for DigestValue");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(DigestValue.class, "Invalid Tag for DigestValue");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(DigestValue.class, "Missing or invalid '@type' attribute for DigestValue");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(DigestValue.class,
                    "Missing or non-text 'value' for DigestValue");
            return null;
        }

        ByteBuffer value = codec.treeToValue(valueNode, ByteBuffer.class);
        DigestValue digestValue = DigestValue.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!digestValue.isSupported()) {
            ctxt.reportInputMismatch(DigestValue.class, "DigestValue not supported for spec " + spec);
            return null;
        }

        return digestValue;
    }
}