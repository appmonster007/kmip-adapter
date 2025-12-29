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
import org.purpleBean.kmip.common.SplitKeyParts;

import java.io.IOException;

public class SplitKeyPartsXmlDeserializer extends KmipDataTypeXmlDeserializer<SplitKeyParts> {
    private final KmipTag kmipTag = SplitKeyParts.kmipTag;
    private final EncodingType encodingType = SplitKeyParts.encodingType;

    @Override
    public SplitKeyParts deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(SplitKeyParts.class, "Expected XML object for SplitKeyParts");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(SplitKeyParts.class, "Invalid Tag for SplitKeyParts");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(SplitKeyParts.class, "Missing or invalid '@type' attribute for SplitKeyParts");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(SplitKeyParts.class,
                    "Missing or non-number 'value' for SplitKeyParts");
            return null;
        }

        int value = Integer.parseInt(valueNode.asText());
        SplitKeyParts splitKeyParts = SplitKeyParts.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!splitKeyParts.isSupported()) {
            ctxt.reportInputMismatch(SplitKeyParts.class, "SplitKeyParts not supported for spec " + spec);
            return null;
        }

        return splitKeyParts;
    }
}