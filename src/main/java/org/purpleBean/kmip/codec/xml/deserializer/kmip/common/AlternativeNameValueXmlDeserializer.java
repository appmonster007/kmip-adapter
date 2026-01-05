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
import org.purpleBean.kmip.common.AlternativeNameValue;

import java.io.IOException;

public class AlternativeNameValueXmlDeserializer extends KmipDataTypeXmlDeserializer<AlternativeNameValue> {
    private final KmipTag kmipTag = AlternativeNameValue.kmipTag;
    private final EncodingType encodingType = AlternativeNameValue.encodingType;

    @Override
    public AlternativeNameValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(AlternativeNameValue.class, "Expected XML object for AlternativeNameValue");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(AlternativeNameValue.class, "Invalid Tag for AlternativeNameValue");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(AlternativeNameValue.class, "Missing or invalid '@type' attribute for AlternativeNameValue");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(AlternativeNameValue.class,
                    "Missing or non-text 'value' for AlternativeNameValue");
            return null;
        }

        AlternativeNameValue alternativeNameValue = AlternativeNameValue.builder().value(valueNode.asText()).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!alternativeNameValue.isSupported()) {
            ctxt.reportInputMismatch(AlternativeNameValue.class, "AlternativeNameValue not supported for spec " + spec);
            return null;
        }

        return alternativeNameValue;
    }
}