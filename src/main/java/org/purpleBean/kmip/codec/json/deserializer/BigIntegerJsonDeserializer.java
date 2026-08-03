package org.purpleBean.kmip.codec.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HexFormat;
import org.purpleBean.kmip.codec.ttlv.TtlvConstants;

public class BigIntegerJsonDeserializer extends JsonDeserializer<BigInteger> {
  @Override
  public BigInteger deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    JsonNode node = p
        .getCodec()
        .readTree(p);
    HexFormat hexFormat = HexFormat.of();
    byte[] data = hexFormat.parseHex(node.asText());

    if (!TtlvConstants.isProperlyPadded(data.length)) {
      throw new IllegalArgumentException("Expected 8n bytes to get value");
    }

    return new BigInteger(data);
  }
}