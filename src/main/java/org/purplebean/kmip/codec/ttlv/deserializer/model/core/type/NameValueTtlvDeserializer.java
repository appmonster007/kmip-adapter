package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.NameValue;

public class NameValueTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<NameValue, NameValue.NameValueBuilder> {

  public NameValueTtlvDeserializer() {
    super(NameValue.kmipTag, NameValue.encodingType);
  }

  @Override
  protected NameValue.NameValueBuilder createBuilder() {
    return NameValue.builder();
  }

  @Override
  protected void setValue(NameValue.NameValueBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected NameValue build(NameValue.NameValueBuilder builder) {
    return builder.build();
  }
}
