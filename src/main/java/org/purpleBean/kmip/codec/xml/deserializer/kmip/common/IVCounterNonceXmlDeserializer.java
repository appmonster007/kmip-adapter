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
import org.purpleBean.kmip.common.IVCounterNonce;

import java.io.IOException;
import java.nio.ByteBuffer;

public class IVCounterNonceXmlDeserializer extends KmipDataTypeXmlDeserializer<IVCounterNonce> {
    private final KmipTag kmipTag = IVCounterNonce.kmipTag;
    private final EncodingType encodingType = IVCounterNonce.encodingType;

    @Override
    public IVCounterNonce deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(IVCounterNonce.class, "Expected XML object for IVCounterNonce");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(IVCounterNonce.class, "Invalid Tag for IVCounterNonce");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(IVCounterNonce.class, "Missing or invalid '@type' attribute for IVCounterNonce");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(IVCounterNonce.class,
                    "Missing or non-text 'value' for IVCounterNonce");
            return null;
        }

        ByteBuffer value = codec.treeToValue(valueNode, ByteBuffer.class);
        IVCounterNonce iVCounterNonce = IVCounterNonce.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!iVCounterNonce.isSupported()) {
            ctxt.reportInputMismatch(IVCounterNonce.class, "IVCounterNonce not supported for spec " + spec);
            return null;
        }

        return iVCounterNonce;
    }
}