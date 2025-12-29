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
import org.purpleBean.kmip.common.DeviceSerialNumber;

import java.io.IOException;

public class DeviceSerialNumberXmlDeserializer extends KmipDataTypeXmlDeserializer<DeviceSerialNumber> {
    private final KmipTag kmipTag = DeviceSerialNumber.kmipTag;
    private final EncodingType encodingType = DeviceSerialNumber.encodingType;

    @Override
    public DeviceSerialNumber deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(DeviceSerialNumber.class, "Expected XML object for DeviceSerialNumber");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(DeviceSerialNumber.class, "Invalid Tag for DeviceSerialNumber");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(DeviceSerialNumber.class, "Missing or invalid '@type' attribute for DeviceSerialNumber");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(DeviceSerialNumber.class,
                    "Missing or non-text 'value' for DeviceSerialNumber");
            return null;
        }

        String value = valueNode.asText();
        DeviceSerialNumber deviceSerialNumber = DeviceSerialNumber.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!deviceSerialNumber.isSupported()) {
            ctxt.reportInputMismatch(DeviceSerialNumber.class, "DeviceSerialNumber not supported for spec " + spec);
            return null;
        }

        return deviceSerialNumber;
    }
}