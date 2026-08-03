package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.DeactivationDate;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v3x0.structure.DeactivationReason;
import org.purplebean.kmip.model.v3x0.structure.request.payload.DeactivateOpRequestPayload;

public class DeactivateOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DeactivateOpRequestPayload,
        DeactivateOpRequestPayload.DeactivateOpRequestPayloadBuilder> {

  public DeactivateOpRequestPayloadTtlvDeserializer() {
    super(DeactivateOpRequestPayload.kmipTag, DeactivateOpRequestPayload.encodingType);
  }

  @Override
  protected DeactivateOpRequestPayload.DeactivateOpRequestPayloadBuilder createBuilder() {
    return DeactivateOpRequestPayload.builder();
  }

  @Override
  protected void setValue(DeactivateOpRequestPayload.DeactivateOpRequestPayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.DEACTIVATION_REASON ->
          builder.deactivationReason(mapper.readValue(p, DeactivationReason.class));
      case KmipTag.Standard.DEACTIVATION_DATE ->
          builder.deactivationDate(mapper.readValue(p, DeactivationDate.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DeactivateOpRequestPayload build(
      DeactivateOpRequestPayload.DeactivateOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}