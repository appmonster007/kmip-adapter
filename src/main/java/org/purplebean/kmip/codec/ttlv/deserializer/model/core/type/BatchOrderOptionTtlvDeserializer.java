package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.BatchOrderOption;

/**
 * TTLV deserializer for {@link BatchOrderOption}.
 */
public class BatchOrderOptionTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<BatchOrderOption,
        BatchOrderOption.BatchOrderOptionBuilder> {

  /**
   * Constructs a new {@link BatchOrderOptionTtlvDeserializer}.
   */
  public BatchOrderOptionTtlvDeserializer() {
    super(BatchOrderOption.kmipTag, BatchOrderOption.encodingType);
  }

  @Override
  protected BatchOrderOption.BatchOrderOptionBuilder createBuilder() {
    return BatchOrderOption.builder();
  }

  @Override
  protected void setValue(BatchOrderOption.BatchOrderOptionBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Boolean.class));
  }

  @Override
  protected BatchOrderOption build(BatchOrderOption.BatchOrderOptionBuilder builder) {
    return builder.build();
  }
}
