package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.BatchOrderOption;

public class BatchOrderOptionTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<BatchOrderOption,
        BatchOrderOption.BatchOrderOptionBuilder> {

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
