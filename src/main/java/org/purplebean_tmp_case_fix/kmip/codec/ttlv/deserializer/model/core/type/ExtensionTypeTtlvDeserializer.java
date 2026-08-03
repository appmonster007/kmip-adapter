package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.ExtensionType;

public class ExtensionTypeTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ExtensionType, ExtensionType.ExtensionTypeBuilder> {

  public ExtensionTypeTtlvDeserializer() {
    super(ExtensionType.kmipTag, ExtensionType.encodingType);
  }

  @Override
  protected ExtensionType.ExtensionTypeBuilder createBuilder() {
    return ExtensionType.builder();
  }

  @Override
  protected void setValue(ExtensionType.ExtensionTypeBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, Integer.class));
  }

  @Override
  protected ExtensionType build(ExtensionType.ExtensionTypeBuilder builder) {
    return builder.build();
  }
}
