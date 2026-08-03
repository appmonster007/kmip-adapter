package org.purplebean.kmip.codec.ttlv.serializer;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.codec.ttlv.TtlvConstants;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvSerializer;

/**
 * TTLV serializer for {@link BigInteger}.
 */
public class BigIntegerTtlvSerializer extends TtlvSerializer<BigInteger> {
  private final EncodingType type = EncodingType.BIG_INTEGER;

  @Override
  public ByteBuffer serialize(BigInteger value, TtlvMapper mapper) throws IOException {
    byte[] valueBytes = value.toByteArray();
    int paddedLength = TtlvConstants.calculatePaddedLength(valueBytes.length);
    ByteBuffer buffer = ByteBuffer.allocate(paddedLength);
    int paddingNeeded = paddedLength - valueBytes.length;
    if (paddingNeeded > 0) {
      byte[] padding = new byte[paddingNeeded];
      Arrays.fill(padding, TtlvConstants.PADDING_BYTE);
      buffer.put(padding);
    }
    buffer.put(valueBytes);
    return buffer;
  }
}
