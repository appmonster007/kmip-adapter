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
import org.purpleBean.kmip.common.DerivationData;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DerivationDataXmlDeserializer extends KmipDataTypeXmlDeserializer<DerivationData> {
    private final KmipTag kmipTag = DerivationData.kmipTag;
    private final EncodingType encodingType = DerivationData.encodingType;

    @Override
    public DerivationData deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(DerivationData.class, "Expected XML object for DerivationData");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(DerivationData.class, "Invalid Tag for DerivationData");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(DerivationData.class, "Missing or invalid '@type' attribute for DerivationData");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(DerivationData.class,
                    "Missing or non-text 'value' for DerivationData");
            return null;
        }

        ByteBuffer value = codec.treeToValue(valueNode, ByteBuffer.class);
        DerivationData derivationData = DerivationData.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!derivationData.isSupported()) {
            ctxt.reportInputMismatch(DerivationData.class, "DerivationData not supported for spec " + spec);
            return null;
        }

        return derivationData;
    }
}