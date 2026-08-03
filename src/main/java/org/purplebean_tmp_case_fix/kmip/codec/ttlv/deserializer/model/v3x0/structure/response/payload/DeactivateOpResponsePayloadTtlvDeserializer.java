package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.response.payload.DeactivateOpResponsePayload;

public class DeactivateOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DeactivateOpResponsePayload,
        DeactivateOpResponsePayload.DeactivateOpResponsePayloadBuilder> {

  public DeactivateOpResponsePayloadTtlvDeserializer() {
    super(DeactivateOpResponsePayload.kmipTag, DeactivateOpResponsePayload.encodingType);
  }

  @Override
  protected DeactivateOpResponsePayload.DeactivateOpResponsePayloadBuilder createBuilder() {
    return DeactivateOpResponsePayload.builder();
  }

  @Override
  protected void setValue(DeactivateOpResponsePayload.DeactivateOpResponsePayloadBuilder builder,
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
  protected DeactivateOpResponsePayload build(
      DeactivateOpResponsePayload.DeactivateOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}