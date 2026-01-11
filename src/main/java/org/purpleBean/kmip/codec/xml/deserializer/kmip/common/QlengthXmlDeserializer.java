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
import org.purpleBean.kmip.common.Qlength;

import java.io.IOException;

public class QlengthXmlDeserializer extends KmipDataTypeXmlDeserializer<Qlength> {
    private final KmipTag kmipTag = Qlength.kmipTag;
    private final EncodingType encodingType = Qlength.encodingType;

    @Override
    public Qlength deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(Qlength.class, "Invalid Tag for Qlength");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        Qlength.QlengthBuilder builder = Qlength.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(Qlength.class, "Missing or invalid 'type' attribute for Qlength");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(Qlength.class,
                                "Missing or non-number 'value' for Qlength");
                        return null;
                    }
                    builder.value(Integer.parseInt(p.getText()));
                }
            }
        }

        Qlength qlength = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!qlength.isSupported()) {
            ctxt.reportInputMismatch(Qlength.class, "Qlength not supported for spec " + spec);
            return null;
        }

        return qlength;
    }
}