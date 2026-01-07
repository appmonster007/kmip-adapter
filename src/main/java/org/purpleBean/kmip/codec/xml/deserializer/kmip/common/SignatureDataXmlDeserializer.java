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
import org.purpleBean.kmip.common.SignatureData;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SignatureDataXmlDeserializer extends KmipDataTypeXmlDeserializer<SignatureData> {
    private final KmipTag kmipTag = SignatureData.kmipTag;
    private final EncodingType encodingType = SignatureData.encodingType;

    @Override
    public SignatureData deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(SignatureData.class, "Expected XML object for SignatureData");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(SignatureData.class, "Invalid Tag for SignatureData");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(SignatureData.class, "Missing or invalid '@type' attribute for SignatureData");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(SignatureData.class,
                    "Missing or non-text 'value' for SignatureData");
            return null;
        }

        ByteBuffer value = codec.treeToValue(valueNode, ByteBuffer.class);
        SignatureData signatureData = SignatureData.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!signatureData.isSupported()) {
            ctxt.reportInputMismatch(SignatureData.class, "SignatureData not supported for spec " + spec);
            return null;
        }

        return signatureData;
    }
}
