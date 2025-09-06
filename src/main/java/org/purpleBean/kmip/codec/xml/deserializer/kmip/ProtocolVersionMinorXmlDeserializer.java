package org.purpleBean.kmip.codec.xml.deserializer.kmip;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.KmipCodecContext;

import java.io.IOException;
import java.util.NoSuchElementException;

public class ProtocolVersionMinorXmlDeserializer extends JsonDeserializer<ProtocolVersion.ProtocolVersionMinor> {

    @Override
    public ProtocolVersion.ProtocolVersionMinor deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(ProtocolVersion.ProtocolVersionMinor.class,
                    "Expected XML element object for ProtocolVersionMinor");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !EncodingType.INTEGER.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(ProtocolVersion.ProtocolVersionMinor.class,
                    "Missing or invalid 'type' attribute for ProtocolVersionMinor");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ProtocolVersion.ProtocolVersionMinor.class,
                    "Missing or non-text 'value' attribute for ProtocolVersionMinor");
            return null;
        }

        int value = Integer.parseInt(valueNode.asText());
        ProtocolVersion.ProtocolVersionMinor minor = ProtocolVersion.ProtocolVersionMinor.of(value);

        KmipSpec spec = KmipCodecContext.getSpec();
        if (!minor.isSupportedFor(spec)) {
            throw new NoSuchElementException("ProtocolVersionMinor " + value + " not supported for spec " + spec);
        }

        return minor;
    }
}
