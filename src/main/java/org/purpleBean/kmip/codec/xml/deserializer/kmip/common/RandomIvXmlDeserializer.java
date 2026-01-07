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
import org.purpleBean.kmip.common.RandomIv;

import java.io.IOException;

public class RandomIvXmlDeserializer extends KmipDataTypeXmlDeserializer<RandomIv> {
    private final KmipTag kmipTag = RandomIv.kmipTag;
    private final EncodingType encodingType = RandomIv.encodingType;

    @Override
    public RandomIv deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(RandomIv.class, "Expected XML object for RandomIv");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(RandomIv.class, "Invalid Tag for RandomIv");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(RandomIv.class, "Missing or invalid '@type' attribute for RandomIv");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(RandomIv.class,
                    "Missing or non-boolean 'value' for RandomIv");
            return null;
        }

        Boolean value = Boolean.valueOf(valueNode.asText());
        RandomIv randomIv = RandomIv.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!randomIv.isSupported()) {
            ctxt.reportInputMismatch(RandomIv.class, "RandomIv not supported for spec " + spec);
            return null;
        }

        return randomIv;
    }
}