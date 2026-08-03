package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.v1x2.structure.response.payload.DiscoverVersionsOpResponsePayload;

/**
 * TTLV deserializer for {@link DiscoverVersionsOpResponsePayload}.
 */
public class DiscoverVersionsOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DiscoverVersionsOpResponsePayload,
        DiscoverVersionsOpResponsePayload.DiscoverVersionsOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link DiscoverVersionsOpResponsePayloadTtlvDeserializer}.
   */
  public DiscoverVersionsOpResponsePayloadTtlvDeserializer() {
    super(DiscoverVersionsOpResponsePayload.kmipTag,
        DiscoverVersionsOpResponsePayload.encodingType);
  }

  @Override
  protected DiscoverVersionsOpResponsePayload.DiscoverVersionsOpResponsePayloadBuilder createBuilder() {
    return DiscoverVersionsOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      DiscoverVersionsOpResponsePayload.DiscoverVersionsOpResponsePayloadBuilder builder,
      byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    if (nodeTag.equals(KmipTag.Standard.PROTOCOL_VERSION)) {
      builder.protocolVersion(mapper.readValue(p, ProtocolVersion.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DiscoverVersionsOpResponsePayload build(
      DiscoverVersionsOpResponsePayload.DiscoverVersionsOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
