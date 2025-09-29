package org.purpleBean.kmip.codec.xml.deserializer.kmip;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.ProtocolVersion;

import java.io.IOException;
import java.util.NoSuchElementException;

public class ProtocolVersionMajorXmlDeserializer extends KmipDataTypeXmlDeserializer<ProtocolVersion.ProtocolVersionMajor> {

    @Override
    public ProtocolVersion.ProtocolVersionMajor deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(ProtocolVersion.ProtocolVersionMajor.class,
                    "Expected XML element object for ProtocolVersionMajor");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !EncodingType.INTEGER.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(ProtocolVersion.ProtocolVersionMajor.class,
                    "Missing or invalid 'type' attribute for ProtocolVersionMajor");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ProtocolVersion.ProtocolVersionMajor.class,
                    "Missing or non-text 'value' attribute for ProtocolVersionMajor");
            return null;
        }

        int value = Integer.parseInt(valueNode.asText());
        ProtocolVersion.ProtocolVersionMajor major = ProtocolVersion.ProtocolVersionMajor.of(value);

        KmipSpec spec = KmipContext.getSpec();
        if (!major.isSupported()) {
            throw new NoSuchElementException("ProtocolVersionMajor " + value + " not supported for spec " + spec);
        }

        return major;
    }
}
