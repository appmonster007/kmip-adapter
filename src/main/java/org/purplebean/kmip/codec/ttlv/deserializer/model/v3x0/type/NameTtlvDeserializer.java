package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v3x0.type.Name;

/**
 * TTLV deserializer for {@link Name}.
 */
public class NameTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<Name, Name.NameBuilder> {

  /**
   * Constructs a new {@link NameTtlvDeserializer}.
   */
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