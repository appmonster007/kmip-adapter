package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.ObtainLeaseOpRequestPayload;

public class ObtainLeaseOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ObtainLeaseOpRequestPayload,
        ObtainLeaseOpRequestPayload.ObtainLeaseOpRequestPayloadBuilder> {

  public ObtainLeaseOpRequestPayloadTtlvDeserializer() {
    super(ObtainLeaseOpRequestPayload.kmipTag, ObtainLeaseOpRequestPayload.encodingType);
  }

  @Override
  protected ObtainLeaseOpRequestPayload.ObtainLeaseOpRequestPayloadBuilder createBuilder() {
    return ObtainLeaseOpRequestPayload.builder();
  }

  @Override
  protected void setValue(ObtainLeaseOpRequestPayload.ObtainLeaseOpRequestPayloadBuilder builder,
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
  protected ObtainLeaseOpRequestPayload build(
      ObtainLeaseOpRequestPayload.ObtainLeaseOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
