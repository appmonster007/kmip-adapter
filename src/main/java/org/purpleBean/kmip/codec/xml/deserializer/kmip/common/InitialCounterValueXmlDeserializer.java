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
import org.purpleBean.kmip.common.InitialCounterValue;

import java.io.IOException;

public class InitialCounterValueXmlDeserializer extends KmipDataTypeXmlDeserializer<InitialCounterValue> {
    private final KmipTag kmipTag = InitialCounterValue.kmipTag;
    private final EncodingType encodingType = InitialCounterValue.encodingType;

    @Override
    public InitialCounterValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(InitialCounterValue.class, "Expected XML object for InitialCounterValue");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(InitialCounterValue.class, "Invalid Tag for InitialCounterValue");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(InitialCounterValue.class, "Missing or invalid '@type' attribute for InitialCounterValue");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(InitialCounterValue.class,
                    "Missing or non-numeric 'value' for InitialCounterValue");
            return null;
        }

        Integer value = Integer.valueOf(valueNode.asText());
        InitialCounterValue initialCounterValue = InitialCounterValue.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!initialCounterValue.isSupported()) {
            ctxt.reportInputMismatch(InitialCounterValue.class, "InitialCounterValue not supported for spec " + spec);
            return null;
        }

        return initialCounterValue;
    }
}