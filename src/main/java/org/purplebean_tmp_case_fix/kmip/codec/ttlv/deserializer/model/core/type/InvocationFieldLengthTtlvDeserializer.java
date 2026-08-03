package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.InvocationFieldLength;

public class InvocationFieldLengthTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<InvocationFieldLength,
        InvocationFieldLength.InvocationFieldLengthBuilder> {

  public InvocationFieldLengthTtlvDeserializer() {
    super(InvocationFieldLength.kmipTag, InvocationFieldLength.encodingType);
  }

  @Override
  protected InvocationFieldLength.InvocationFieldLengthBuilder createBuilder() {
    return InvocationFieldLength.builder();
  }

  @Override
  protected void setValue(InvocationFieldLength.InvocationFieldLengthBuilder builder, byte[] tag,
                          byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Integer.class));
  }

  @Override
  protected InvocationFieldLength build(
      InvocationFieldLength.InvocationFieldLengthBuilder builder) {
    return builder.build();
  }
}
