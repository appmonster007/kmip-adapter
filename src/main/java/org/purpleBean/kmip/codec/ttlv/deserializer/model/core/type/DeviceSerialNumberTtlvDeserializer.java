package org.purplebean.kmip.codec.ttlv.deserializer.model.core.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.DeviceSerialNumber;

public class DeviceSerialNumberTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DeviceSerialNumber,
        DeviceSerialNumber.DeviceSerialNumberBuilder> {

  public DeviceSerialNumberTtlvDeserializer() {
    super(DeviceSerialNumber.kmipTag, DeviceSerialNumber.encodingType);
  }

  @Override
  protected DeviceSerialNumber.DeviceSerialNumberBuilder createBuilder() {
    return DeviceSerialNumber.builder();
  }

  @Override
  protected void setValue(DeviceSerialNumber.DeviceSerialNumberBuilder builder, byte[] tag,
                          byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
    builder.value(mapper.readValue(byteBuffer, String.class));
  }

  @Override
  protected DeviceSerialNumber build(DeviceSerialNumber.DeviceSerialNumberBuilder builder) {
    return builder.build();
  }
}
