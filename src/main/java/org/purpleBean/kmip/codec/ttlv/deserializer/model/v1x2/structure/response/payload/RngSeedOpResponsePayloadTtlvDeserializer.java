package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.DataLength;
import org.purplebean.kmip.model.v1x2.structure.response.payload.RngSeedOpResponsePayload;

public class RngSeedOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<RngSeedOpResponsePayload,
        RngSeedOpResponsePayload.RngSeedOpResponsePayloadBuilder> {

  public RngSeedOpResponsePayloadTtlvDeserializer() {
    super(RngSeedOpResponsePayload.kmipTag, RngSeedOpResponsePayload.encodingType);
  }

  @Override
  protected RngSeedOpResponsePayload.RngSeedOpResponsePayloadBuilder createBuilder() {
    return RngSeedOpResponsePayload.builder();
  }

  @Override
  protected void setValue(RngSeedOpResponsePayload.RngSeedOpResponsePayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    if (nodeTag.equals(KmipTag.Standard.DATA_LENGTH)) {
      builder.dataLength(mapper.readValue(p, DataLength.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected RngSeedOpResponsePayload build(
      RngSeedOpResponsePayload.RngSeedOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
