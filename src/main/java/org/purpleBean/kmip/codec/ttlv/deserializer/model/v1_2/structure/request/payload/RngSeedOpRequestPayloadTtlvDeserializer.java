package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.RngSeedOpRequestPayload;

public class RngSeedOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<RngSeedOpRequestPayload,
        RngSeedOpRequestPayload.RngSeedOpRequestPayloadBuilder> {

  public RngSeedOpRequestPayloadTtlvDeserializer() {
    super(RngSeedOpRequestPayload.kmipTag, RngSeedOpRequestPayload.encodingType);
  }

  @Override
  protected RngSeedOpRequestPayload.RngSeedOpRequestPayloadBuilder createBuilder() {
    return RngSeedOpRequestPayload.builder();
  }

  @Override
  protected void setValue(RngSeedOpRequestPayload.RngSeedOpRequestPayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    if (nodeTag.equals(KmipTag.Standard.DATA)) {
      builder.data(mapper.readValue(p, DataByteString.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected RngSeedOpRequestPayload build(
      RngSeedOpRequestPayload.RngSeedOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
