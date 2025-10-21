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
import org.purpleBean.kmip.common.LeaseTime;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Map;

public class LeaseTimeXmlDeserializer extends KmipDataTypeXmlDeserializer<LeaseTime> {
    private final KmipTag kmipTag = LeaseTime.kmipTag;
    private final EncodingType encodingType = LeaseTime.encodingType;

    @Override
    public LeaseTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(LeaseTime.class, "Expected XML object for LeaseTime");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
              && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(LeaseTime.class, "Invalid Tag for LeaseTime");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(LeaseTime.class, "Missing or invalid '@type' attribute for LeaseTime");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(LeaseTime.class,
                "Missing or non-text 'value' for LeaseTime");
            return null;
        }

        LeaseTime leaseTime = LeaseTime.builder().value(valueNode.asInt()).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!leaseTime.isSupported()) {
            ctxt.reportInputMismatch(LeaseTime.class, "LeaseTime not supported for spec " + spec);
            return null;
        }

        return leaseTime;
    }
}
