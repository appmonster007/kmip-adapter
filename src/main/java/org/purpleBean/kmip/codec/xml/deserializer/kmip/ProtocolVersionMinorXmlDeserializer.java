package org.purpleBean.kmip.codec.xml.deserializer.kmip;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.ProtocolVersion;

import java.io.IOException;
import java.util.NoSuchElementException;

public class ProtocolVersionMinorXmlDeserializer extends KmipDataTypeXmlDeserializer<ProtocolVersion.ProtocolVersionMinor> {

    @Override
    public ProtocolVersion.ProtocolVersionMinor deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.currentToken() == null) {
            p.nextToken();
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        int value = -1;

        while (p.nextToken() != JsonToken.END_OBJECT) {
            if (p.currentToken() == JsonToken.FIELD_NAME) {
                String fieldName = p.currentName();

                p.nextToken(); // Move to the value token
                if ("type".equalsIgnoreCase(fieldName)) {
                    String type = p.getText();
                    if (!EncodingType.INTEGER.getDescription().equals(type)) {
                        ctxt.reportInputMismatch(ProtocolVersion.ProtocolVersionMinor.class, "Missing or invalid 'type' attribute for ProtocolVersionMinor");
                        return null;
                    }
                }
                if ("value".equalsIgnoreCase(fieldName)) {
                    if (p.hasTextCharacters()) {
                        ctxt.reportInputMismatch(ProtocolVersion.ProtocolVersionMinor.class,
                                "Missing or non-text 'value' attribute for ProtocolVersionMinor");
                        return null;
                    }
                    value = Integer.parseInt(p.getText());
                }
            }
        }

        if (value == -1) {
            ctxt.reportInputMismatch(ProtocolVersion.ProtocolVersionMinor.class, "Missing 'value' for ProtocolVersionMinor");
            return null;
        }

        ProtocolVersion.ProtocolVersionMinor minor = ProtocolVersion.ProtocolVersionMinor.of(value);

        KmipSpec spec = KmipContext.getSpec();
        if (!minor.isSupported()) {
            throw new NoSuchElementException("ProtocolVersionMinor " + value + " not supported for spec " + spec);
        }

        return minor;
    }
}
