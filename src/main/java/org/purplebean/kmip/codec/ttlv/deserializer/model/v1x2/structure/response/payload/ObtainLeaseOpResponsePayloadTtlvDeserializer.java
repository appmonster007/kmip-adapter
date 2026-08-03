package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.LastChangeDate;
import org.purplebean.kmip.model.core.type.LeaseTime;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.ObtainLeaseOpResponsePayload;

/**
 * TTLV deserializer for {@link ObtainLeaseOpResponsePayload}.
 */
public class ObtainLeaseOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ObtainLeaseOpResponsePayload,
        ObtainLeaseOpResponsePayload.ObtainLeaseOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link ObtainLeaseOpResponsePayloadTtlvDeserializer}.
   */
  public ObtainLeaseOpResponsePayloadTtlvDeserializer() {
    super(ObtainLeaseOpResponsePayload.kmipTag, ObtainLeaseOpResponsePayload.encodingType);
  }

  @Override
  protected ObtainLeaseOpResponsePayload.ObtainLeaseOpResponsePayloadBuilder createBuilder() {
    return ObtainLeaseOpResponsePayload.builder();
  }

  @Override
  protected void setValue(ObtainLeaseOpResponsePayload.ObtainLeaseOpResponsePayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.LEASE_TIME -> builder.leaseTime(mapper.readValue(p, LeaseTime.class));
      case KmipTag.Standard.LAST_CHANGE_DATE ->
          builder.lastChangeDate(mapper.readValue(p, LastChangeDate.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ObtainLeaseOpResponsePayload build(
      ObtainLeaseOpResponsePayload.ObtainLeaseOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
