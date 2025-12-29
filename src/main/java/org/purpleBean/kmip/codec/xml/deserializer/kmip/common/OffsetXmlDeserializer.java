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
import org.purpleBean.kmip.common.Offset;

import java.io.IOException;

public class OffsetXmlDeserializer extends KmipDataTypeXmlDeserializer<Offset> {
    private final KmipTag kmipTag = Offset.kmipTag;
    private final EncodingType encodingType = Offset.encodingType;

    @Override
    public Offset deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(Offset.class, "Expected XML object for Offset");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(Offset.class, "Invalid Tag for Offset");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(Offset.class, "Missing or invalid '@type' attribute for Offset");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(Offset.class,
                    "Missing or non-number 'value' for Offset");
            return null;
        }

        int value = Integer.parseInt(valueNode.asText());
        Offset offset = Offset.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!offset.isSupported()) {
            ctxt.reportInputMismatch(Offset.class, "Offset not supported for spec " + spec);
            return null;
        }

        return offset;
    }
}