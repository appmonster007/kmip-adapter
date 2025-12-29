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
import org.purpleBean.kmip.common.G;

import java.io.IOException;
import java.math.BigInteger;

public class GXmlDeserializer extends KmipDataTypeXmlDeserializer<G> {
    private final KmipTag kmipTag = G.kmipTag;
    private final EncodingType encodingType = G.encodingType;

    @Override
    public G deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(G.class, "Expected XML object for G");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(G.class, "Invalid Tag for G");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(G.class, "Missing or invalid '@type' attribute for G");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(G.class,
                    "Missing or non-text 'value' for G");
            return null;
        }

        BigInteger value = codec.treeToValue(valueNode, BigInteger.class);
        G g = G.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!g.isSupported()) {
            ctxt.reportInputMismatch(G.class, "G not supported for spec " + spec);
            return null;
        }

        return g;
    }
}