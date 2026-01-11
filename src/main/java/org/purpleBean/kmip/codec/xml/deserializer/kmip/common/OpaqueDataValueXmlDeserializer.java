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
import org.purpleBean.kmip.common.OpaqueDataValue;

import java.io.IOException;
import java.nio.ByteBuffer;

public class OpaqueDataValueXmlDeserializer extends KmipDataTypeXmlDeserializer<OpaqueDataValue> {
    private final KmipTag kmipTag = OpaqueDataValue.kmipTag;
    private final EncodingType encodingType = OpaqueDataValue.encodingType;

    @Override
    public OpaqueDataValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(OpaqueDataValue.class, "Invalid Tag for OpaqueDataValue");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        OpaqueDataValue.OpaqueDataValueBuilder builder = OpaqueDataValue.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(OpaqueDataValue.class, "Missing or invalid 'type' attribute for OpaqueDataValue");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(OpaqueDataValue.class,
                                "Missing or non-text 'value' for OpaqueDataValue");
                        return null;
                    }
                    builder.value(ctxt.readValue(p, ByteBuffer.class));
                }
            }
        }

        OpaqueDataValue opaqueDataValue = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!opaqueDataValue.isSupported()) {
            ctxt.reportInputMismatch(OpaqueDataValue.class, "OpaqueDataValue not supported for spec " + spec);
            return null;
        }

        return opaqueDataValue;
    }
}