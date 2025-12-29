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
import org.purpleBean.kmip.common.Password;

import java.io.IOException;

public class PasswordXmlDeserializer extends KmipDataTypeXmlDeserializer<Password> {
    private final KmipTag kmipTag = Password.kmipTag;
    private final EncodingType encodingType = Password.encodingType;

    @Override
    public Password deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(Password.class, "Expected XML object for Password");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(Password.class, "Invalid Tag for Password");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(Password.class, "Missing or invalid '@type' attribute for Password");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(Password.class,
                    "Missing or non-text 'value' for Password");
            return null;
        }

        String value = valueNode.asText();
        Password password = Password.builder().value(value).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!password.isSupported()) {
            ctxt.reportInputMismatch(Password.class, "Password not supported for spec " + spec);
            return null;
        }

        return password;
    }
}