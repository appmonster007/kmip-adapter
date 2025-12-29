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
import org.purpleBean.kmip.common.UniqueBatchItemID;

import java.io.IOException;
import java.nio.ByteBuffer;

public class UniqueBatchItemIDXmlDeserializer extends KmipDataTypeXmlDeserializer<UniqueBatchItemID> {
    private final KmipTag kmipTag = UniqueBatchItemID.kmipTag;
    private final EncodingType encodingType = UniqueBatchItemID.encodingType;

    @Override
    public UniqueBatchItemID deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(UniqueBatchItemID.class, "Expected XML object for UniqueBatchItemID");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(UniqueBatchItemID.class, "Invalid Tag for UniqueBatchItemID");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(UniqueBatchItemID.class, "Missing or invalid '@type' attribute for UniqueBatchItemID");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(UniqueBatchItemID.class,
                    "Missing or non-text 'value' for UniqueBatchItemID");
            return null;
        }

        ByteBuffer value = codec.treeToValue(valueNode, ByteBuffer.class);
        UniqueBatchItemID uniqueBatchItemID = UniqueBatchItemID.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!uniqueBatchItemID.isSupported()) {
            ctxt.reportInputMismatch(UniqueBatchItemID.class, "UniqueBatchItemID not supported for spec " + spec);
            return null;
        }

        return uniqueBatchItemID;
    }
}