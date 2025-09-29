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
import org.purpleBean.kmip.common.ActivationDate;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.NoSuchElementException;

/**
 * XML deserializer for ActivationDate.
 */
public class ActivationDateXmlDeserializer extends KmipDataTypeXmlDeserializer<ActivationDate> {
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ACTIVATION_DATE);
    private final EncodingType encodingType = EncodingType.DATE_TIME; // TODO : update the encoding type

    @Override
    public ActivationDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(ActivationDate.class, "Expected XML element object for ActivationDate");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(ActivationDate.class, "Invalid Tag for ActivationDate");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(ActivationDate.class, "Missing or invalid '@type' attribute for ActivationDate");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ActivationDate.class,
                "Missing or non-text 'value' for ActivationDate");
            return null;
        }

        OffsetDateTime dateTime = OffsetDateTime.parse(valueNode.asText());
        ActivationDate attribute = ActivationDate.builder().value(dateTime).build();

        KmipSpec spec = KmipContext.getSpec();
        if (!attribute.isSupported()) {
            throw new NoSuchElementException(
                String.format("ActivationDate '%s' not supported for spec %s", kmipTag.getDescription(), spec));

        }
        return attribute;
    }
}
