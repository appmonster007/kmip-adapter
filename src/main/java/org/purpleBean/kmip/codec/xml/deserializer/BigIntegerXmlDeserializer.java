package org.purpleBean.kmip.codec.xml.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HexFormat;

public class BigIntegerXmlDeserializer extends JsonDeserializer<BigInteger> {
    @Override
    public BigInteger deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        // For XML, we need to handle the text content properly
        String hexString = p.getCodec().readValue(p, String.class);

        if (hexString == null || hexString.trim().isEmpty()) {
            throw new IllegalArgumentException("BigInteger hex string cannot be null or empty");
        }

        // Remove any whitespace and handle potential JSON-like formatting
        hexString = hexString.trim();


        // Parse hex string to bytes
        HexFormat hexFormat = HexFormat.of();
        byte[] data;
        try {
            data = hexFormat.parseHex(hexString);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid hex string for BigInteger: '" + hexString + "'", e);
        }

        if (!TtlvConstants.isProperlyPadded(data.length)) {
            throw new IllegalArgumentException("Expected 8n bytes to get value, got " + data.length + " bytes");
        }

        return new BigInteger(data);
    }
}