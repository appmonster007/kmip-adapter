package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
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
        if (p.currentToken() == null) {
            p.nextToken();
        }

        String currentName;
        if (p instanceof FromXmlParser xmlParser) {
            currentName = xmlParser.getStaxReader().getLocalName();
        } else {
            currentName = (String) ctxt.getAttribute("tag");
        }

        if (!kmipTag.getDescription().equalsIgnoreCase(currentName)) {
            ctxt.reportInputMismatch(SerialNumber.class, "Invalid Tag for SerialNumber");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        SerialNumber.SerialNumberBuilder builder = SerialNumber.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(SerialNumber.class, "Missing or invalid 'type' attribute for SerialNumber");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(SerialNumber.class,
                                "Missing or non-text 'value' for SerialNumber");
                        return null;
                    }
                    builder.value(p.getText());
                }
            }
        }

        SerialNumber serialNumber = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!serialNumber.isSupported()) {
            ctxt.reportInputMismatch(SerialNumber.class, "SerialNumber not supported for spec " + spec);
            return null;
        }

        return serialNumber;
    }
}