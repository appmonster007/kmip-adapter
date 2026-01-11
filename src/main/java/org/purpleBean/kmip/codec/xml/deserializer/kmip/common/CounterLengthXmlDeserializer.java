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
import org.purpleBean.kmip.common.CounterLength;

import java.io.IOException;

public class CounterLengthXmlDeserializer extends KmipDataTypeXmlDeserializer<CounterLength> {
    private final KmipTag kmipTag = CounterLength.kmipTag;
    private final EncodingType encodingType = CounterLength.encodingType;

    @Override
    public CounterLength deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(CounterLength.class, "Invalid Tag for CounterLength");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        CounterLength.CounterLengthBuilder builder = CounterLength.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(CounterLength.class, "Missing or invalid 'type' attribute for CounterLength");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(CounterLength.class,
                                "Missing or non-numeric 'value' for CounterLength");
                        return null;
                    }
                    builder.value(Integer.valueOf(p.getText()));
                }
            }
        }

        CounterLength counterLength = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!counterLength.isSupported()) {
            ctxt.reportInputMismatch(CounterLength.class, "CounterLength not supported for spec " + spec);
            return null;
        }

        return counterLength;
    }
}