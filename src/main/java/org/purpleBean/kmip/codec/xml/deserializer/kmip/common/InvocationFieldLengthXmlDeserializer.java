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
import org.purpleBean.kmip.common.InvocationFieldLength;

import java.io.IOException;

public class InvocationFieldLengthXmlDeserializer extends KmipDataTypeXmlDeserializer<InvocationFieldLength> {
    private final KmipTag kmipTag = InvocationFieldLength.kmipTag;
    private final EncodingType encodingType = InvocationFieldLength.encodingType;

    @Override
    public InvocationFieldLength deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(InvocationFieldLength.class, "Expected XML object for InvocationFieldLength");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(InvocationFieldLength.class, "Invalid Tag for InvocationFieldLength");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(InvocationFieldLength.class, "Missing or invalid '@type' attribute for InvocationFieldLength");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(InvocationFieldLength.class,
                    "Missing or non-numeric 'value' for InvocationFieldLength");
            return null;
        }

        Integer value = Integer.valueOf(valueNode.asText());
        InvocationFieldLength invocationFieldLength = InvocationFieldLength.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!invocationFieldLength.isSupported()) {
            ctxt.reportInputMismatch(InvocationFieldLength.class, "InvocationFieldLength not supported for spec " + spec);
            return null;
        }

        return invocationFieldLength;
    }
}