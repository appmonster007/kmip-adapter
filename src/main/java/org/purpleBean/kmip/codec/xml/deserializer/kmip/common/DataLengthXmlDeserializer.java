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
import org.purpleBean.kmip.common.DataLength;

import java.io.IOException;

public class DataLengthXmlDeserializer extends KmipDataTypeXmlDeserializer<DataLength> {
    private final KmipTag kmipTag = DataLength.kmipTag;
    private final EncodingType encodingType = DataLength.encodingType;

    @Override
    public DataLength deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(DataLength.class, "Invalid Tag for DataLength");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        DataLength.DataLengthBuilder builder = DataLength.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(DataLength.class, "Missing or invalid 'type' attribute for DataLength");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(DataLength.class,
                                "Missing or non-numeric 'value' for DataLength");
                        return null;
                    }
                    builder.value(Integer.valueOf(p.getText()));
                }
            }
        }

        DataLength dataLength = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!dataLength.isSupported()) {
            ctxt.reportInputMismatch(DataLength.class, "DataLength not supported for spec " + spec);
            return null;
        }

        return dataLength;
    }
}