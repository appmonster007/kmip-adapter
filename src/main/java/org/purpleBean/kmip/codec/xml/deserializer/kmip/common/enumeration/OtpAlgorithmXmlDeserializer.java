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
import org.purpleBean.kmip.common.enumeration.OtpAlgorithm;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for OtpAlgorithm.
 */
public class OtpAlgorithmXmlDeserializer extends KmipDataTypeXmlDeserializer<OtpAlgorithm> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.OTP_ALGORITHM);

    @Override
    public OtpAlgorithm deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(OtpAlgorithm.class, "Expected XML element object for OtpAlgorithm");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(OtpAlgorithm.class, "Invalid Tag for OtpAlgorithm");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(OtpAlgorithm.class, "Missing or invalid '@type' attribute for OtpAlgorithm");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(OtpAlgorithm.class, "Missing or non-text '@value' attribute for OtpAlgorithm");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        OtpAlgorithm otpalgorithm = new OtpAlgorithm(OtpAlgorithm.fromName(spec, description));
        if (!otpalgorithm.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                    String.format("OtpAlgorithm '%s' not supported for spec %s", description, spec));
        }

        return otpalgorithm;
    }
}
