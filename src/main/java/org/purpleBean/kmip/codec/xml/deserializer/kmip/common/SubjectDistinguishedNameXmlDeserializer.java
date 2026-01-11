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
import org.purpleBean.kmip.common.SubjectDistinguishedName;

import java.io.IOException;
import java.nio.ByteBuffer;

public class SubjectDistinguishedNameXmlDeserializer extends KmipDataTypeXmlDeserializer<SubjectDistinguishedName> {
    private final KmipTag kmipTag = SubjectDistinguishedName.kmipTag;
    private final EncodingType encodingType = SubjectDistinguishedName.encodingType;

    @Override
    public SubjectDistinguishedName deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(SubjectDistinguishedName.class, "Invalid Tag for SubjectDistinguishedName");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        SubjectDistinguishedName.SubjectDistinguishedNameBuilder builder = SubjectDistinguishedName.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(SubjectDistinguishedName.class, "Missing or invalid 'type' attribute for SubjectDistinguishedName");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(SubjectDistinguishedName.class,
                                "Missing or non-text 'value' for SubjectDistinguishedName");
                        return null;
                    }
                    builder.value(ctxt.readValue(p, ByteBuffer.class));
                }
            }
        }

        SubjectDistinguishedName subjectDistinguishedName = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!subjectDistinguishedName.isSupported()) {
            ctxt.reportInputMismatch(SubjectDistinguishedName.class, "SubjectDistinguishedName not supported for spec " + spec);
            return null;
        }

        return subjectDistinguishedName;
    }
}