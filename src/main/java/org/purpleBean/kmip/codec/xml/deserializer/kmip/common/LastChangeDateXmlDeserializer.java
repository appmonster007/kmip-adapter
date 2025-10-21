package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.LastChangeDate;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Map;

public class LastChangeDateXmlDeserializer extends KmipDataTypeXmlDeserializer<LastChangeDate> {
    private final KmipTag kmipTag = LastChangeDate.kmipTag;
    private final EncodingType encodingType = LastChangeDate.encodingType;

    @Override
    public LastChangeDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(LastChangeDate.class, "Expected XML object for LastChangeDate");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
              && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(LastChangeDate.class, "Invalid Tag for LastChangeDate");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(LastChangeDate.class, "Missing or invalid '@type' attribute for LastChangeDate");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(LastChangeDate.class,
                "Missing or non-text 'value' for LastChangeDate");
            return null;
        }

        OffsetDateTime dateTime = OffsetDateTime.parse(valueNode.asText());
        LastChangeDate lastChangeDate = LastChangeDate.builder().value(dateTime).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!lastChangeDate.isSupported()) {
            ctxt.reportInputMismatch(LastChangeDate.class, "LastChangeDate not supported for spec " + spec);
            return null;
        }

        return lastChangeDate;
    }
}
