package org.purplebean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.CancellationResult;

/**
 * TTLV deserializer for {@link CancellationResult}.
 */
public class CancellationResultTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CancellationResult,
        CancellationResult.CancellationResultBuilder> {

  /**
   * Constructs a new {@link CancellationResultTtlvDeserializer}.
   */
  public CancellationResultTtlvDeserializer() {
    super(CancellationResult.kmipTag, CancellationResult.encodingType);
  }

  @Override
  protected CancellationResult.CancellationResultBuilder createBuilder() {
    return CancellationResult.builder();
  }

  @Override
  protected void setValue(CancellationResult.CancellationResultBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(CancellationResult.fromValue(value));
  }

  @Override
  protected CancellationResult build(CancellationResult.CancellationResultBuilder builder) {
    return builder.build();
  }
}
