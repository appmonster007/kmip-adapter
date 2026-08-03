package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.ExtensionName;

/**
 * TTLV deserializer for {@link ExtensionName}.
 */
public class ExtensionNameTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ExtensionName, ExtensionName.ExtensionNameBuilder> {

  /**
   * Constructs a new {@link ExtensionNameTtlvDeserializer}.
   */
  public ExtensionNameTtlvDeserializer() {
    super(ExtensionName.kmipTag, ExtensionName.encodingType);
  }

  @Override
  protected ExtensionName.ExtensionNameBuilder createBuilder() {
    return ExtensionName.builder();
  }

  @Override
  protected void setValue(ExtensionName.ExtensionNameBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected ExtensionName build(ExtensionName.ExtensionNameBuilder builder) {
    return builder.build();
  }
}
