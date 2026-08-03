package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.Username;

public class UsernameTtlvDeserializer
    extends AbstractKmipDataTypeTtlvDeserializer<Username, Username.UsernameBuilder> {

  public UsernameTtlvDeserializer() {
    super(Username.kmipTag, Username.encodingType);
  }

  @Override
  protected Username.UsernameBuilder createBuilder() {
    return Username.builder();
  }

  @Override
  protected void setValue(Username.UsernameBuilder builder, byte[] tag, byte type, ByteBuffer p,
                          TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(p, String.class));
  }

  @Override
  protected Username build(Username.UsernameBuilder builder) {
    return builder.build();
  }
}