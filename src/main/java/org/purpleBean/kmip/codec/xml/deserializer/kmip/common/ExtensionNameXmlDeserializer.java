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
import org.purpleBean.kmip.common.ExtensionName;

import java.io.IOException;

public class ExtensionNameXmlDeserializer extends KmipDataTypeXmlDeserializer<ExtensionName> {
    private final KmipTag kmipTag = ExtensionName.kmipTag;
    private final EncodingType encodingType = ExtensionName.encodingType;

    @Override
    public ExtensionName deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(ExtensionName.class, "Expected XML object for ExtensionName");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(ExtensionName.class, "Invalid Tag for ExtensionName");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(ExtensionName.class, "Missing or invalid '@type' attribute for ExtensionName");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ExtensionName.class,
                    "Missing or non-text 'value' for ExtensionName");
            return null;
        }

        String value = valueNode.asText();
        ExtensionName extensionName = ExtensionName.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!extensionName.isSupported()) {
            ctxt.reportInputMismatch(ExtensionName.class, "ExtensionName not supported for spec " + spec);
            return null;
        }

        return extensionName;
    }
}