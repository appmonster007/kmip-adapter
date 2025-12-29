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
import org.purpleBean.kmip.common.SerialNumber;

import java.io.IOException;

public class SerialNumberXmlDeserializer extends KmipDataTypeXmlDeserializer<SerialNumber> {
    private final KmipTag kmipTag = SerialNumber.kmipTag;
    private final EncodingType encodingType = SerialNumber.encodingType;

    @Override
    public SerialNumber deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(SerialNumber.class, "Expected XML object for SerialNumber");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(SerialNumber.class, "Invalid Tag for SerialNumber");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(SerialNumber.class, "Missing or invalid '@type' attribute for SerialNumber");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(SerialNumber.class,
                    "Missing or non-text 'value' for SerialNumber");
            return null;
        }

        String value = valueNode.asText();
        SerialNumber serialNumber = SerialNumber.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!serialNumber.isSupported()) {
            ctxt.reportInputMismatch(SerialNumber.class, "SerialNumber not supported for spec " + spec);
            return null;
        }

        return serialNumber;
    }
}