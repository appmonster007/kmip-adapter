package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.type.RotateNameValue;

public class RotateNameValueTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<RotateNameValue, RotateNameValue.RotateNameValueBuilder> {

  public RotateNameValueTtlvDeserializer() {
    super(RotateNameValue.kmipTag, RotateNameValue.encodingType);
  }

  @Override
  protected RotateNameValue.RotateNameValueBuilder createBuilder() {
    return RotateNameValue.builder();
  }

  @Override
  protected void setValue(RotateNameValue.RotateNameValueBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected RotateNameValue build(RotateNameValue.RotateNameValueBuilder builder) {
    return builder.build();
  }
}