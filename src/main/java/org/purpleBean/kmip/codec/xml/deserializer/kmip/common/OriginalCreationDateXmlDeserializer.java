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
import org.purpleBean.kmip.common.OriginalCreationDate;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.NoSuchElementException;

/**
 * XML deserializer for OriginalCreationDate.
 */
public class OriginalCreationDateXmlDeserializer extends KmipDataTypeXmlDeserializer<OriginalCreationDate> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ORIGINAL_CREATION_DATE);
    private final EncodingType encodingType = EncodingType.DATE_TIME; // TODO : update the encoding type

    @Override
    public OriginalCreationDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(OriginalCreationDate.class, "Invalid Tag for OriginalCreationDate");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        OriginalCreationDate.OriginalCreationDateBuilder builder = OriginalCreationDate.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(OriginalCreationDate.class, "Missing or invalid 'type' attribute for OriginalCreationDate");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(OriginalCreationDate.class,
                                "Missing or non-text 'value' for OriginalCreationDate");
                        return null;
                    }
                    builder.value(OffsetDateTime.parse(p.getText()));
                }
            }
        }

        OriginalCreationDate attribute = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!attribute.isSupported()) {
            throw new NoSuchElementException(
                    String.format("OriginalCreationDate '%s' not supported for spec %s", kmipTag.getDescription(), spec));

        }
        return attribute;
    }
}