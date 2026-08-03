package org.purplebean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.DeviceCredential;
import org.purplebean.kmip.model.core.type.DeviceIdentifier;
import org.purplebean.kmip.model.core.type.DeviceSerialNumber;
import org.purplebean.kmip.model.core.type.MachineIdentifier;
import org.purplebean.kmip.model.core.type.MediaIdentifier;
import org.purplebean.kmip.model.core.type.NetworkIdentifier;
import org.purplebean.kmip.model.core.type.Password;

/**
 * JSON deserializer for {@link DeviceCredential}.
 */
public class DeviceCredentialJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<DeviceCredential,
        DeviceCredential.DeviceCredentialBuilder> {

  /**
   * Constructs a new {@link DeviceCredentialJsonDeserializer}.
   */
  public DeviceCredentialJsonDeserializer() {
    super(DeviceCredential.kmipTag, DeviceCredential.encodingType);
  }

  @Override
  protected DeviceCredential.DeviceCredentialBuilder createBuilder() {
    return DeviceCredential.builder();
  }

  @Override
  protected void setValue(DeviceCredential.DeviceCredentialBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.DEVICE_SERIAL_NUMBER ->
          builder.deviceSerialNumber(ctxt.readValue(p, DeviceSerialNumber.class));
      case KmipTag.Standard.PASSWORD -> builder.password(ctxt.readValue(p, Password.class));
      case KmipTag.Standard.DEVICE_IDENTIFIER ->
          builder.deviceIdentifier(ctxt.readValue(p, DeviceIdentifier.class));
      case KmipTag.Standard.NETWORK_IDENTIFIER ->
          builder.networkIdentifier(ctxt.readValue(p, NetworkIdentifier.class));
      case KmipTag.Standard.MACHINE_IDENTIFIER ->
          builder.machineIdentifier(ctxt.readValue(p, MachineIdentifier.class));
      case KmipTag.Standard.MEDIA_IDENTIFIER ->
          builder.mediaIdentifier(ctxt.readValue(p, MediaIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DeviceCredential build(DeviceCredential.DeviceCredentialBuilder builder) {
    return builder.build();
  }
}
