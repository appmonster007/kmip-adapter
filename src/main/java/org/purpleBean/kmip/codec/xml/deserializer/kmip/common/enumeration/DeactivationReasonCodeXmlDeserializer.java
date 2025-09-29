package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

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
import org.purpleBean.kmip.common.enumeration.DeactivationReasonCode;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for DeactivationReasonCode.
 */
public class DeactivationReasonCodeXmlDeserializer extends KmipDataTypeXmlDeserializer<DeactivationReasonCode> {
    private final KmipTag kmipTag = DeactivationReasonCode.kmipTag;
    private final EncodingType encodingType = DeactivationReasonCode.encodingType;

    @Override
    public DeactivationReasonCode deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(DeactivationReasonCode.class, "Expected XML element object for DeactivationReasonCode");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(DeactivationReasonCode.class, "Invalid Tag for DeactivationReasonCode");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(DeactivationReasonCode.class, "Missing or invalid '@type' attribute for DeactivationReasonCode");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(DeactivationReasonCode.class, "Missing or non-text '@value' attribute for DeactivationReasonCode");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        DeactivationReasonCode deactivationreasoncode = new DeactivationReasonCode(DeactivationReasonCode.fromName(description));
        if (!deactivationreasoncode.isSupported()) {
            throw new NoSuchElementException(
                String.format("DeactivationReasonCode '%s' not supported for spec %s", description, spec));
        }

        return deactivationreasoncode;
    }
}
