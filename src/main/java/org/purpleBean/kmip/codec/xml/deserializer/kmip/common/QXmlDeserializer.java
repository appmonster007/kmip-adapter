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
import org.purpleBean.kmip.common.Q;

import java.io.IOException;
import java.math.BigInteger;

public class QXmlDeserializer extends KmipDataTypeXmlDeserializer<Q> {
    private final KmipTag kmipTag = Q.kmipTag;
    private final EncodingType encodingType = Q.encodingType;

    @Override
    public Q deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(Q.class, "Invalid Tag for Q");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        Q.QBuilder builder = Q.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(Q.class, "Missing or invalid 'type' attribute for Q");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(Q.class,
                                "Missing or non-text 'value' for Q");
                        return null;
                    }
                    builder.value(ctxt.readValue(p, BigInteger.class));
                }
            }
        }

        Q q = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!q.isSupported()) {
            ctxt.reportInputMismatch(Q.class, "Q not supported for spec " + spec);
            return null;
        }

        return q;
    }
}