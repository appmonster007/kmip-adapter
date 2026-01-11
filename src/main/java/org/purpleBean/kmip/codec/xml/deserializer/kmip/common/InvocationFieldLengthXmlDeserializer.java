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
import org.purpleBean.kmip.common.InvocationFieldLength;

import java.io.IOException;

public class InvocationFieldLengthXmlDeserializer extends KmipDataTypeXmlDeserializer<InvocationFieldLength> {
    private final KmipTag kmipTag = InvocationFieldLength.kmipTag;
    private final EncodingType encodingType = InvocationFieldLength.encodingType;

    @Override
    public InvocationFieldLength deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(InvocationFieldLength.class, "Invalid Tag for InvocationFieldLength");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        InvocationFieldLength.InvocationFieldLengthBuilder builder = InvocationFieldLength.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(InvocationFieldLength.class, "Missing or invalid 'type' attribute for InvocationFieldLength");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(InvocationFieldLength.class,
                                "Missing or non-numeric 'value' for InvocationFieldLength");
                        return null;
                    }
                    builder.value(Integer.valueOf(p.getText()));
                }
            }
        }

        InvocationFieldLength invocationFieldLength = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!invocationFieldLength.isSupported()) {
            ctxt.reportInputMismatch(InvocationFieldLength.class, "InvocationFieldLength not supported for spec " + spec);
            return null;
        }

        return invocationFieldLength;
    }
}