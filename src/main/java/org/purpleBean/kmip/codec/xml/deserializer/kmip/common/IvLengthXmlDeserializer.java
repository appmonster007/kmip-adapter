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
import org.purpleBean.kmip.common.IvLength;

import java.io.IOException;

public class IvLengthXmlDeserializer extends KmipDataTypeXmlDeserializer<IvLength> {
    private final KmipTag kmipTag = IvLength.kmipTag;
    private final EncodingType encodingType = IvLength.encodingType;

    @Override
    public IvLength deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(IvLength.class, "Expected XML object for IvLength");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(IvLength.class, "Invalid Tag for IvLength");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(IvLength.class, "Missing or invalid '@type' attribute for IvLength");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(IvLength.class,
                    "Missing or non-numeric 'value' for IvLength");
            return null;
        }

        Integer value = Integer.valueOf(valueNode.asText());
        IvLength ivLength = IvLength.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!ivLength.isSupported()) {
            ctxt.reportInputMismatch(IvLength.class, "IvLength not supported for spec " + spec);
            return null;
        }

        return ivLength;
    }
}