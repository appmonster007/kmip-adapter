package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.IvLength;

public class IvLengthTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<IvLength, IvLength.IvLengthBuilder> {

  public IvLengthTtlvDeserializer() {
    super(IvLength.kmipTag, IvLength.encodingType);
  }

  @Override
  protected IvLength.IvLengthBuilder createBuilder() {
    return IvLength.builder();
  }

  @Override
  protected void setValue(IvLength.IvLengthBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Integer.class));
  }

  @Override
  protected IvLength build(IvLength.IvLengthBuilder builder) {
    return builder.build();
  }
}
