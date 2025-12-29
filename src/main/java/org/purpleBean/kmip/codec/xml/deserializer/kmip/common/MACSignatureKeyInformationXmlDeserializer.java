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
import org.purpleBean.kmip.common.MACSignatureKeyInformation;

import java.io.IOException;

public class MACSignatureKeyInformationXmlDeserializer extends KmipDataTypeXmlDeserializer<MACSignatureKeyInformation> {
    private final KmipTag kmipTag = MACSignatureKeyInformation.kmipTag;
    private final EncodingType encodingType = MACSignatureKeyInformation.encodingType;

    @Override
    public MACSignatureKeyInformation deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(MACSignatureKeyInformation.class, "Expected XML object for MACSignatureKeyInformation");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(MACSignatureKeyInformation.class, "Invalid Tag for MACSignatureKeyInformation");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(MACSignatureKeyInformation.class, "Missing or invalid '@type' attribute for MACSignatureKeyInformation");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(MACSignatureKeyInformation.class,
                    "Missing or non-text 'value' for MACSignatureKeyInformation");
            return null;
        }

        String value = valueNode.asText();
        MACSignatureKeyInformation mACSignatureKeyInformation = MACSignatureKeyInformation.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!mACSignatureKeyInformation.isSupported()) {
            ctxt.reportInputMismatch(MACSignatureKeyInformation.class, "MACSignatureKeyInformation not supported for spec " + spec);
            return null;
        }

        return mACSignatureKeyInformation;
    }
}