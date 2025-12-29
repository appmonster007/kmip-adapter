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
import org.purpleBean.kmip.common.Qlength;

import java.io.IOException;

public class QlengthXmlDeserializer extends KmipDataTypeXmlDeserializer<Qlength> {
    private final KmipTag kmipTag = Qlength.kmipTag;
    private final EncodingType encodingType = Qlength.encodingType;

    @Override
    public Qlength deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(Qlength.class, "Expected XML object for Qlength");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(Qlength.class, "Invalid Tag for Qlength");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(Qlength.class, "Missing or invalid '@type' attribute for Qlength");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(Qlength.class,
                    "Missing or non-number 'value' for Qlength");
            return null;
        }

        int value = Integer.parseInt(valueNode.asText());
        Qlength qlength = Qlength.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!qlength.isSupported()) {
            ctxt.reportInputMismatch(Qlength.class, "Qlength not supported for spec " + spec);
            return null;
        }

        return qlength;
    }
}