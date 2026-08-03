package org.purplebean.kmip.codec.ttlv.deserializer;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Objects;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.codec.ttlv.TtlvConstants;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;

public class BigIntegerTtlvDeserializer extends TtlvDeserializer<BigInteger> {
  private final EncodingType type = EncodingType.BIG_INTEGER;

  @Override
  public BigInteger deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws IOException {
    Objects.requireNonNull(ttlvBuffer);
    if (!TtlvConstants.isProperlyPadded(ttlvBuffer.remaining())) {
      throw new IllegalArgumentException("Expected 8n bytes to get value");
    }

    return new BigInteger(ttlvBuffer.array());
  }
}
