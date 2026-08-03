package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3x0.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v3x0.structure.request.payload.ObliterateOpRequestPayload;

public class ObliterateOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ObliterateOpRequestPayload,
        ObliterateOpRequestPayload.ObliterateOpRequestPayloadBuilder> {

  public ObliterateOpRequestPayloadTtlvDeserializer() {
    super(ObliterateOpRequestPayload.kmipTag, ObliterateOpRequestPayload.encodingType);
  }

  @Override
  protected ObliterateOpRequestPayload.ObliterateOpRequestPayloadBuilder createBuilder() {
    return ObliterateOpRequestPayload.builder();
  }

  @Override
  protected void setValue(ObliterateOpRequestPayload.ObliterateOpRequestPayloadBuilder builder,
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
  protected ObliterateOpRequestPayload build(
      ObliterateOpRequestPayload.ObliterateOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}