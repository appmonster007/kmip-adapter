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
import org.purpleBean.kmip.common.DeviceIdentifier;

import java.io.IOException;

public class DeviceIdentifierXmlDeserializer extends KmipDataTypeXmlDeserializer<DeviceIdentifier> {
    private final KmipTag kmipTag = DeviceIdentifier.kmipTag;
    private final EncodingType encodingType = DeviceIdentifier.encodingType;

    @Override
    public DeviceIdentifier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(DeviceIdentifier.class, "Expected XML object for DeviceIdentifier");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(DeviceIdentifier.class, "Invalid Tag for DeviceIdentifier");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(DeviceIdentifier.class, "Missing or invalid '@type' attribute for DeviceIdentifier");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(DeviceIdentifier.class,
                    "Missing or non-text 'value' for DeviceIdentifier");
            return null;
        }

        String value = valueNode.asText();
        DeviceIdentifier deviceIdentifier = DeviceIdentifier.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!deviceIdentifier.isSupported()) {
            ctxt.reportInputMismatch(DeviceIdentifier.class, "DeviceIdentifier not supported for spec " + spec);
            return null;
        }

        return deviceIdentifier;
    }
}