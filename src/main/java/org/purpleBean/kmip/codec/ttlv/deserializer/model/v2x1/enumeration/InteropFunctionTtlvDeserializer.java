package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.enumeration;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.enumeration.InteropFunction;

public class InteropFunctionTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<InteropFunction, InteropFunction.InteropFunctionBuilder> {

  public InteropFunctionTtlvDeserializer() {
    super(InteropFunction.kmipTag, InteropFunction.encodingType);
  }

  @Override
  protected InteropFunction.InteropFunctionBuilder createBuilder() {
    return InteropFunction.builder();
  }

  @Override
  protected void setValue(InteropFunction.InteropFunctionBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    Integer value = mapper.readValue(p, Integer.class);
    builder.value(InteropFunction.fromValue(value));
  }

  @Override
  protected InteropFunction build(InteropFunction.InteropFunctionBuilder builder) {
    return builder.build();
  }
}
