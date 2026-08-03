package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.ReProvisionOpResponsePayload;

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