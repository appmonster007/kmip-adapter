package org.purpleBean.kmip.codec.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HexFormat;

public class BigIntegerJsonDeserializer extends JsonDeserializer<BigInteger> {
    @Override
    public BigInteger deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        HexFormat hexFormat = HexFormat.of();
        byte[] data = hexFormat.parseHex(p.getText());

        if (!TtlvConstants.isProperlyPadded(data.length)) {
            throw new IllegalArgumentException("Expected 8n bytes to get value");
        }

        return new BigInteger(data);
    }
}