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
import org.purpleBean.kmip.common.FixedFieldLength;

import java.io.IOException;

public class FixedFieldLengthXmlDeserializer extends KmipDataTypeXmlDeserializer<FixedFieldLength> {
    private final KmipTag kmipTag = FixedFieldLength.kmipTag;
    private final EncodingType encodingType = FixedFieldLength.encodingType;

    @Override
    public FixedFieldLength deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(FixedFieldLength.class, "Expected XML object for FixedFieldLength");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(FixedFieldLength.class, "Invalid Tag for FixedFieldLength");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(FixedFieldLength.class, "Missing or invalid '@type' attribute for FixedFieldLength");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(FixedFieldLength.class,
                    "Missing or non-numeric 'value' for FixedFieldLength");
            return null;
        }

        Integer value = Integer.valueOf(valueNode.asText());
        FixedFieldLength fixedFieldLength = FixedFieldLength.of(value);

        KmipSpec spec = KmipContext.getSpec();

        if (!fixedFieldLength.isSupported()) {
            ctxt.reportInputMismatch(FixedFieldLength.class, "FixedFieldLength not supported for spec " + spec);
            return null;
        }

        return fixedFieldLength;
    }
}