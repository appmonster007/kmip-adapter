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
import org.purpleBean.kmip.common.NameValue;

import java.io.IOException;

public class NameValueXmlDeserializer extends KmipDataTypeXmlDeserializer<NameValue> {
    private final KmipTag kmipTag = NameValue.kmipTag;
    private final EncodingType encodingType = NameValue.encodingType;

    @Override
    public NameValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(NameValue.class, "Expected XML object for NameValue");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(NameValue.class, "Invalid Tag for NameValue");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(NameValue.class, "Missing or invalid '@type' attribute for NameValue");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(NameValue.class,
                    "Missing or non-text 'value' for NameValue");
            return null;
        }

        NameValue nameValue = NameValue.builder().value(valueNode.asText()).build();

        KmipSpec spec = KmipContext.getSpec();
        if (!nameValue.isSupportedFor(spec)) {
            ctxt.reportInputMismatch(NameValue.class, "NameValue not supported for spec " + spec);
            return null;
        }

        return nameValue;
    }
}
