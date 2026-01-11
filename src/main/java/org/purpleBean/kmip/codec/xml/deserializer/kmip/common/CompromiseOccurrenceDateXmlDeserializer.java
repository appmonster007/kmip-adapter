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
import org.purpleBean.kmip.common.CompromiseOccurrenceDate;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.NoSuchElementException;

/**
 * XML deserializer for CompromiseOccurrenceDate.
 */
public class CompromiseOccurrenceDateXmlDeserializer extends KmipDataTypeXmlDeserializer<CompromiseOccurrenceDate> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.COMPROMISE_OCCURRENCE_DATE);
    private final EncodingType encodingType = EncodingType.DATE_TIME; // TODO : update the encoding type

    @Override
    public CompromiseOccurrenceDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
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
            ctxt.reportInputMismatch(CompromiseOccurrenceDate.class, "Invalid Tag for CompromiseOccurrenceDate");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        CompromiseOccurrenceDate.CompromiseOccurrenceDateBuilder builder = CompromiseOccurrenceDate.builder();

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!encodingType.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(CompromiseOccurrenceDate.class, "Missing or invalid 'type' attribute for CompromiseOccurrenceDate");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(CompromiseOccurrenceDate.class,
                                "Missing or non-text 'value' for CompromiseOccurrenceDate");
                        return null;
                    }
                    builder.value(OffsetDateTime.parse(p.getText()));
                }
            }
        }

        CompromiseOccurrenceDate attribute = builder.build();

        KmipSpec spec = KmipContext.getSpec();
        if (!attribute.isSupported()) {
            throw new NoSuchElementException(
                    String.format("CompromiseOccurrenceDate '%s' not supported for spec %s", kmipTag.getDescription(), spec));

        }
        return attribute;
    }
}