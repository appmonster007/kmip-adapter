package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1x2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1x2.structure.request.payload.DestroyOpRequestPayload;

public class DestroyOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DestroyOpRequestPayload,
        DestroyOpRequestPayload.DestroyOpRequestPayloadBuilder> {

  public DestroyOpRequestPayloadTtlvDeserializer() {
    super(DestroyOpRequestPayload.kmipTag, DestroyOpRequestPayload.encodingType);
  }

  @Override
  protected DestroyOpRequestPayload.DestroyOpRequestPayloadBuilder createBuilder() {
    return DestroyOpRequestPayload.builder();
  }

  @Override
  protected void setValue(DestroyOpRequestPayload.DestroyOpRequestPayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    if (nodeTag.equals(KmipTag.Standard.UNIQUE_IDENTIFIER)) {
      builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DestroyOpRequestPayload build(
      DestroyOpRequestPayload.DestroyOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
