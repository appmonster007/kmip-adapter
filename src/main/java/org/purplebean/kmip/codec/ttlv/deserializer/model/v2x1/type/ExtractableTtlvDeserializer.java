package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.Extractable;

public class ExtractableTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<Extractable, Extractable.ExtractableBuilder> {

  public ExtractableTtlvDeserializer() {
    super(Extractable.kmipTag, Extractable.encodingType);
  }

  @Override
  protected Extractable.ExtractableBuilder createBuilder() {
    return Extractable.builder();
  }

  @Override
  protected void setValue(Extractable.ExtractableBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, Boolean.class));
  }

  @Override
  protected Extractable build(Extractable.ExtractableBuilder builder) {
    return builder.build();
  }
}