package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.Fresh;

public class FreshTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<Fresh, Fresh.FreshBuilder> {

  public FreshTtlvDeserializer() {
    super(Fresh.kmipTag, Fresh.encodingType);
  }

  @Override
  protected Fresh.FreshBuilder createBuilder() {
    return Fresh.builder();
  }

  @Override
  protected void setValue(Fresh.FreshBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer,
                          TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Boolean.class));
  }

  @Override
  protected Fresh build(Fresh.FreshBuilder builder) {
    return builder.build();
  }
}
