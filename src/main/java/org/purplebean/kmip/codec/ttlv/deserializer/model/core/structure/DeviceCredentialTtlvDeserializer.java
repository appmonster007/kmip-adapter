package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.DeviceCredential;
import org.purplebean.kmip.model.core.type.DeviceIdentifier;
import org.purplebean.kmip.model.core.type.DeviceSerialNumber;
import org.purplebean.kmip.model.core.type.MachineIdentifier;
import org.purplebean.kmip.model.core.type.MediaIdentifier;
import org.purplebean.kmip.model.core.type.NetworkIdentifier;
import org.purplebean.kmip.model.core.type.Password;

/**
 * TTLV deserializer for {@link DeviceCredential}.
 */
public class DeviceCredentialTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DeviceCredential,
        DeviceCredential.DeviceCredentialBuilder> {

  /**
   * Constructs a new {@link DeviceCredentialTtlvDeserializer}.
   */
  public DeviceCredentialTtlvDeserializer() {
    super(DeviceCredential.kmipTag, DeviceCredential.encodingType);
  }

  @Override
  protected DeviceCredential.DeviceCredentialBuilder createBuilder() {
    return DeviceCredential.builder();
  }

  @Override
  protected void setValue(DeviceCredential.DeviceCredentialBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.DEVICE_SERIAL_NUMBER ->
          builder.deviceSerialNumber(mapper.readValue(p, DeviceSerialNumber.class));
      case KmipTag.Standard.PASSWORD -> builder.password(mapper.readValue(p, Password.class));
      case KmipTag.Standard.DEVICE_IDENTIFIER ->
          builder.deviceIdentifier(mapper.readValue(p, DeviceIdentifier.class));
      case KmipTag.Standard.NETWORK_IDENTIFIER ->
          builder.networkIdentifier(mapper.readValue(p, NetworkIdentifier.class));
      case KmipTag.Standard.MACHINE_IDENTIFIER ->
          builder.machineIdentifier(mapper.readValue(p, MachineIdentifier.class));
      case KmipTag.Standard.MEDIA_IDENTIFIER ->
          builder.mediaIdentifier(mapper.readValue(p, MediaIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DeviceCredential build(DeviceCredential.DeviceCredentialBuilder builder) {
    return builder.build();
  }
}