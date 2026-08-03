package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.KeyPartIdentifier;

public class KeyPartIdentifierTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<KeyPartIdentifier,
        KeyPartIdentifier.KeyPartIdentifierBuilder> {

  public KeyPartIdentifierTtlvDeserializer() {
    super(KeyPartIdentifier.kmipTag, KeyPartIdentifier.encodingType);
  }

  @Override
  protected KeyPartIdentifier.KeyPartIdentifierBuilder createBuilder() {
    return KeyPartIdentifier.builder();
  }

  @Override
  protected void setValue(KeyPartIdentifier.KeyPartIdentifierBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Integer.class));
  }

  @Override
  protected KeyPartIdentifier build(KeyPartIdentifier.KeyPartIdentifierBuilder builder) {
    return builder.build();
  }
}
