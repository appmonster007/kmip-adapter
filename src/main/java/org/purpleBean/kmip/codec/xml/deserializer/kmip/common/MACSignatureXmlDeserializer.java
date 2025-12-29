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
import org.purpleBean.kmip.common.MACSignature;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MACSignatureXmlDeserializer extends KmipDataTypeXmlDeserializer<MACSignature> {
    private final KmipTag kmipTag = MACSignature.kmipTag;
    private final EncodingType encodingType = MACSignature.encodingType;

    @Override
    public MACSignature deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(MACSignature.class, "Expected XML object for MACSignature");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(MACSignature.class, "Invalid Tag for MACSignature");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(MACSignature.class, "Missing or invalid '@type' attribute for MACSignature");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(MACSignature.class,
                    "Missing or non-text 'value' for MACSignature");
            return null;
        }

        ByteBuffer value = codec.treeToValue(valueNode, ByteBuffer.class);
        MACSignature mACSignature = MACSignature.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!mACSignature.isSupported()) {
            ctxt.reportInputMismatch(MACSignature.class, "MACSignature not supported for spec " + spec);
            return null;
        }

        return mACSignature;
    }
}