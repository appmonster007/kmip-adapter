package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.GetAttributeListOpRequestPayload;

public class GetAttributeListOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<GetAttributeListOpRequestPayload,
        GetAttributeListOpRequestPayload.GetAttributeListOpRequestPayloadBuilder> {

  public GetAttributeListOpRequestPayloadTtlvDeserializer() {
    super(GetAttributeListOpRequestPayload.kmipTag, GetAttributeListOpRequestPayload.encodingType);
  }

  @Override
  protected GetAttributeListOpRequestPayload.GetAttributeListOpRequestPayloadBuilder createBuilder() {
    return GetAttributeListOpRequestPayload.builder();
  }

  @Override
  protected void setValue(
      GetAttributeListOpRequestPayload.GetAttributeListOpRequestPayloadBuilder builder, byte[] tag,
      byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    if (nodeTag.equals(KmipTag.Standard.UNIQUE_IDENTIFIER)) {
      builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected GetAttributeListOpRequestPayload build(
      GetAttributeListOpRequestPayload.GetAttributeListOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
