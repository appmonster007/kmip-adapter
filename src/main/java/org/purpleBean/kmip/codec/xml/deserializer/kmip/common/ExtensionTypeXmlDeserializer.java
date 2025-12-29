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
import org.purpleBean.kmip.common.ExtensionType;

import java.io.IOException;

public class ExtensionTypeXmlDeserializer extends KmipDataTypeXmlDeserializer<ExtensionType> {
    private final KmipTag kmipTag = ExtensionType.kmipTag;
    private final EncodingType encodingType = ExtensionType.encodingType;

    @Override
    public ExtensionType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(ExtensionType.class, "Expected XML object for ExtensionType");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(ExtensionType.class, "Invalid Tag for ExtensionType");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(ExtensionType.class, "Missing or invalid '@type' attribute for ExtensionType");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ExtensionType.class,
                    "Missing or non-number 'value' for ExtensionType");
            return null;
        }

        int value = Integer.parseInt(valueNode.asText());
        ExtensionType extensionType = ExtensionType.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!extensionType.isSupported()) {
            ctxt.reportInputMismatch(ExtensionType.class, "ExtensionType not supported for spec " + spec);
            return null;
        }

        return extensionType;
    }
}