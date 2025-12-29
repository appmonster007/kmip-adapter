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
import org.purpleBean.kmip.common.MaximumItems;

import java.io.IOException;

public class MaximumItemsXmlDeserializer extends KmipDataTypeXmlDeserializer<MaximumItems> {
    private final KmipTag kmipTag = MaximumItems.kmipTag;
    private final EncodingType encodingType = MaximumItems.encodingType;

    @Override
    public MaximumItems deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(MaximumItems.class, "Expected XML object for MaximumItems");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(MaximumItems.class, "Invalid Tag for MaximumItems");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(MaximumItems.class, "Missing or invalid '@type' attribute for MaximumItems");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(MaximumItems.class,
                    "Missing or non-number 'value' for MaximumItems");
            return null;
        }

        int value = Integer.parseInt(valueNode.asText());
        MaximumItems maximumItems = MaximumItems.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!maximumItems.isSupported()) {
            ctxt.reportInputMismatch(MaximumItems.class, "MaximumItems not supported for spec " + spec);
            return null;
        }

        return maximumItems;
    }
}