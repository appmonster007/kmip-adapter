package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.UniqueBatchItemID;

/**
 * TTLV deserializer for {@link UniqueBatchItemID}.
 */
public class UniqueBatchItemIDTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<UniqueBatchItemID,
        UniqueBatchItemID.UniqueBatchItemIDBuilder> {

  /**
   * Constructs a new {@link UniqueBatchItemIDTtlvDeserializer}.
   */
  public UniqueBatchItemIDTtlvDeserializer() {
    super(UniqueBatchItemID.kmipTag, UniqueBatchItemID.encodingType);
  }

  @Override
  protected UniqueBatchItemID.UniqueBatchItemIDBuilder createBuilder() {
    return UniqueBatchItemID.builder();
  }

  @Override
  protected void setValue(UniqueBatchItemID.UniqueBatchItemIDBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, ByteBuffer.class));
  }

  @Override
  protected UniqueBatchItemID build(UniqueBatchItemID.UniqueBatchItemIDBuilder builder) {
    return builder.build();
  }
}
