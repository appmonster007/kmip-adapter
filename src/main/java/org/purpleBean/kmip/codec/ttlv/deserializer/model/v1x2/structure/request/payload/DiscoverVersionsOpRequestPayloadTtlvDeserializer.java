package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.ProtocolVersion;
import org.purplebean.kmip.model.v1x2.structure.request.payload.DiscoverVersionsOpRequestPayload;

public class DiscoverVersionsOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DiscoverVersionsOpRequestPayload,
        DiscoverVersionsOpRequestPayload.DiscoverVersionsOpRequestPayloadBuilder> {

  public DiscoverVersionsOpRequestPayloadTtlvDeserializer() {
    super(DiscoverVersionsOpRequestPayload.kmipTag, DiscoverVersionsOpRequestPayload.encodingType);
  }

  @Override
  protected DiscoverVersionsOpRequestPayload.DiscoverVersionsOpRequestPayloadBuilder createBuilder() {
    return DiscoverVersionsOpRequestPayload.builder();
  }

  @Override
  protected void setValue(
      DiscoverVersionsOpRequestPayload.DiscoverVersionsOpRequestPayloadBuilder builder, byte[] tag,
      byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    if (nodeTag.equals(KmipTag.Standard.PROTOCOL_VERSION)) {
      builder.protocolVersion(mapper.readValue(p, ProtocolVersion.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DiscoverVersionsOpRequestPayload build(
      DiscoverVersionsOpRequestPayload.DiscoverVersionsOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
