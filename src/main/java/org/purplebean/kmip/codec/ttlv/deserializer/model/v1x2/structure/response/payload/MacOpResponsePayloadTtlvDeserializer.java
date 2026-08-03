package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.MacData;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.MacOpResponsePayload;

/**
 * TTLV deserializer for {@link MacOpResponsePayload}.
 */
public class MacOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<MacOpResponsePayload,
        MacOpResponsePayload.MacOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link MacOpResponsePayloadTtlvDeserializer}.
   */
  public MacOpResponsePayloadTtlvDeserializer() {
    super(MacOpResponsePayload.kmipTag, MacOpResponsePayload.encodingType);
  }

  @Override
  protected MacOpResponsePayload.MacOpResponsePayloadBuilder createBuilder() {
    return MacOpResponsePayload.builder();
  }

  @Override
  protected void setValue(MacOpResponsePayload.MacOpResponsePayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.MAC_DATA -> builder.macData(mapper.readValue(p, MacData.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected MacOpResponsePayload build(MacOpResponsePayload.MacOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
