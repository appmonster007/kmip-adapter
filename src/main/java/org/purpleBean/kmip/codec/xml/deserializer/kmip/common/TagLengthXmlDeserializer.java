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
import org.purpleBean.kmip.common.TagLength;

import java.io.IOException;

public class TagLengthXmlDeserializer extends KmipDataTypeXmlDeserializer<TagLength> {
    private final KmipTag kmipTag = TagLength.kmipTag;
    private final EncodingType encodingType = TagLength.encodingType;

    @Override
    public TagLength deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(TagLength.class, "Expected XML object for TagLength");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(TagLength.class, "Invalid Tag for TagLength");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(TagLength.class, "Missing or invalid '@type' attribute for TagLength");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(TagLength.class,
                    "Missing or non-numeric 'value' for TagLength");
            return null;
        }

        Integer value = Integer.valueOf(valueNode.asText());
        TagLength tagLength = TagLength.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!tagLength.isSupported()) {
            ctxt.reportInputMismatch(TagLength.class, "TagLength not supported for spec " + spec);
            return null;
        }

        return tagLength;
    }
}