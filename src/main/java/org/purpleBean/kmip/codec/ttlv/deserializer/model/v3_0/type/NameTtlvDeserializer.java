package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3_0.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v3_0.type.Name;

public class NameTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<Name, Name.NameBuilder> {

  public NameTtlvDeserializer() {
    super(Name.kmipTag, Name.encodingType);
  }

  @Override
  protected Name.NameBuilder createBuilder() {
    return Name.builder();
  }

  @Override
  protected void setValue(Name.NameBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected Name build(Name.NameBuilder builder) {
    return builder.build();
  }
}