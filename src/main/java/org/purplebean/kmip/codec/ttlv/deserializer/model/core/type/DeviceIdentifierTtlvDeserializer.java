package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.DeviceIdentifier;

/**
 * TTLV deserializer for {@link DeviceIdentifier}.
 */
public class DeviceIdentifierTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DeviceIdentifier,
        DeviceIdentifier.DeviceIdentifierBuilder> {

  /**
   * Constructs a new {@link DeviceIdentifierTtlvDeserializer}.
   */
  public DeviceIdentifierTtlvDeserializer() {
    super(DeviceIdentifier.kmipTag, DeviceIdentifier.encodingType);
  }

  @Override
  protected DeviceIdentifier.DeviceIdentifierBuilder createBuilder() {
    return DeviceIdentifier.builder();
  }

  @Override
  protected void setValue(DeviceIdentifier.DeviceIdentifierBuilder builder, byte[] tag, byte type,
                          ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected DeviceIdentifier build(DeviceIdentifier.DeviceIdentifierBuilder builder) {
    return builder.build();
  }
}
