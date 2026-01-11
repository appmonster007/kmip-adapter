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
import org.purpleBean.kmip.common.Offset;

import java.io.IOException;

public class OffsetXmlDeserializer extends KmipDataTypeXmlDeserializer<Offset> {
    private final KmipTag kmipTag = Offset.kmipTag;
    private final EncodingType encodingType = Offset.encodingType;

    @Override
    public Offset deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(Offset.class, "Invalid Tag for Offset");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        Offset.OffsetBuilder builder = Offset.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(Offset.class, "Missing or invalid 'type' attribute for Offset");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(Offset.class,
                                "Missing or non-number 'value' for Offset");
                        return null;
                    }
                    builder.value(Integer.parseInt(p.getText()));
                }
            }
        }

        Offset offset = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!offset.isSupported()) {
            ctxt.reportInputMismatch(Offset.class, "Offset not supported for spec " + spec);
            return null;
        }

        return offset;
    }
}