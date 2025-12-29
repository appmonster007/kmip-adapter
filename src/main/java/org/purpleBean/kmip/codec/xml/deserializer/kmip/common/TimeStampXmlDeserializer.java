package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.TimeStamp;

import java.io.IOException;
import java.time.OffsetDateTime;

public class TimeStampXmlDeserializer extends KmipDataTypeXmlDeserializer<TimeStamp> {
    private final KmipTag kmipTag = TimeStamp.kmipTag;
    private final EncodingType encodingType = TimeStamp.encodingType;

    @Override
    public TimeStamp deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(TimeStamp.class, "Expected XML object for TimeStamp");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(TimeStamp.class, "Invalid Tag for TimeStamp");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(TimeStamp.class, "Missing or invalid '@type' attribute for TimeStamp");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(TimeStamp.class,
                    "Missing or non-text 'value' for TimeStamp");
            return null;
        }

        OffsetDateTime dateTime = OffsetDateTime.parse(valueNode.asText());
        TimeStamp timeStamp = TimeStamp.builder().value(dateTime).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!timeStamp.isSupported()) {
            ctxt.reportInputMismatch(TimeStamp.class, "TimeStamp not supported for spec " + spec);
            return null;
        }

        return timeStamp;
    }
}