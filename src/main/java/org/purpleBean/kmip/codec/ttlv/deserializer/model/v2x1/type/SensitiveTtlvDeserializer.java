package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.Sensitive;

public class SensitiveTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<Sensitive, Sensitive.SensitiveBuilder> {

  public SensitiveTtlvDeserializer() {
    super(Sensitive.kmipTag, Sensitive.encodingType);
  }

  @Override
  protected Sensitive.SensitiveBuilder createBuilder() {
    return Sensitive.builder();
  }

  @Override
  protected void setValue(Sensitive.SensitiveBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Boolean.class));
  }

  @Override
  protected Sensitive build(Sensitive.SensitiveBuilder builder) {
    return builder.build();
  }
}