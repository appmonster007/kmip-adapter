package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.ReProvisionOpResponsePayload;

public class ReProvisionOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ReProvisionOpResponsePayload,
        ReProvisionOpResponsePayload.ReProvisionOpResponsePayloadBuilder> {

  public ReProvisionOpResponsePayloadTtlvDeserializer() {
    super(ReProvisionOpResponsePayload.kmipTag, ReProvisionOpResponsePayload.encodingType);
  }

  @Override
  protected ReProvisionOpResponsePayload.ReProvisionOpResponsePayloadBuilder createBuilder() {
    return ReProvisionOpResponsePayload.builder();
  }

  @Override
  protected void setValue(ReProvisionOpResponsePayload.ReProvisionOpResponsePayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ReProvisionOpResponsePayload build(
      ReProvisionOpResponsePayload.ReProvisionOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}