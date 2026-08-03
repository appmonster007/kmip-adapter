package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1x2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.v1x2.structure.response.payload.DiscoverVersionsOpResponsePayload;

public class DiscoverVersionsOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DiscoverVersionsOpResponsePayload,
        DiscoverVersionsOpResponsePayload.DiscoverVersionsOpResponsePayloadBuilder> {

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
