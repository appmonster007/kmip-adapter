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
import org.purpleBean.kmip.common.DeviceIdentifier;

import java.io.IOException;

public class DeviceIdentifierXmlDeserializer extends KmipDataTypeXmlDeserializer<DeviceIdentifier> {
    private final KmipTag kmipTag = DeviceIdentifier.kmipTag;
    private final EncodingType encodingType = DeviceIdentifier.encodingType;

    @Override
    public DeviceIdentifier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(DeviceIdentifier.class, "Invalid Tag for DeviceIdentifier");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        DeviceIdentifier.DeviceIdentifierBuilder builder = DeviceIdentifier.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(DeviceIdentifier.class, "Missing or invalid 'type' attribute for DeviceIdentifier");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(DeviceIdentifier.class,
                                "Missing or non-text 'value' for DeviceIdentifier");
                        return null;
                    }
                    builder.value(p.getText());
                }
            }
        }

        DeviceIdentifier deviceIdentifier = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!deviceIdentifier.isSupported()) {
            ctxt.reportInputMismatch(DeviceIdentifier.class, "DeviceIdentifier not supported for spec " + spec);
            return null;
        }

        return deviceIdentifier;
    }
}