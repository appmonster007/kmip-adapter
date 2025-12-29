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
import org.purpleBean.kmip.common.ExtensionTag;

import java.io.IOException;

public class ExtensionTagXmlDeserializer extends KmipDataTypeXmlDeserializer<ExtensionTag> {
    private final KmipTag kmipTag = ExtensionTag.kmipTag;
    private final EncodingType encodingType = ExtensionTag.encodingType;

    @Override
    public ExtensionTag deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(ExtensionTag.class, "Expected XML object for ExtensionTag");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(ExtensionTag.class, "Invalid Tag for ExtensionTag");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(ExtensionTag.class, "Missing or invalid '@type' attribute for ExtensionTag");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ExtensionTag.class,
                    "Missing or non-number 'value' for ExtensionTag");
            return null;
        }

        int value = Integer.parseInt(valueNode.asText());
        ExtensionTag extensionTag = ExtensionTag.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!extensionTag.isSupported()) {
            ctxt.reportInputMismatch(ExtensionTag.class, "ExtensionTag not supported for spec " + spec);
            return null;
        }

        return extensionTag;
    }
}