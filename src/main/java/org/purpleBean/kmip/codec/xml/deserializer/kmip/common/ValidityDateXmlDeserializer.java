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
import org.purpleBean.kmip.common.ValidityDate;

import java.io.IOException;
import java.time.OffsetDateTime;

public class ValidityDateXmlDeserializer extends KmipDataTypeXmlDeserializer<ValidityDate> {
    private final KmipTag kmipTag = ValidityDate.kmipTag;
    private final EncodingType encodingType = ValidityDate.encodingType;

    @Override
    public ValidityDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(ValidityDate.class, "Expected XML object for ValidityDate");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(ValidityDate.class, "Invalid Tag for ValidityDate");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(ValidityDate.class, "Missing or invalid '@type' attribute for ValidityDate");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ValidityDate.class,
                    "Missing or non-text 'value' for ValidityDate");
            return null;
        }

        OffsetDateTime dateTime = OffsetDateTime.parse(valueNode.asText());
        ValidityDate validityDate = ValidityDate.builder().value(dateTime).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!validityDate.isSupported()) {
            ctxt.reportInputMismatch(ValidityDate.class, "ValidityDate not supported for spec " + spec);
            return null;
        }

        return validityDate;
    }
}