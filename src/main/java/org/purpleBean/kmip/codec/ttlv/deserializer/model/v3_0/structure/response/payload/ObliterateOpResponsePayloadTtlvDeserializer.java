package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3_0.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v3_0.structure.response.payload.ObliterateOpResponsePayload;

public class ObliterateOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ObliterateOpResponsePayload,
        ObliterateOpResponsePayload.ObliterateOpResponsePayloadBuilder> {

  public ObliterateOpResponsePayloadTtlvDeserializer() {
    super(ObliterateOpResponsePayload.kmipTag, ObliterateOpResponsePayload.encodingType);
  }

  @Override
  protected ObliterateOpResponsePayload.ObliterateOpResponsePayloadBuilder createBuilder() {
    return ObliterateOpResponsePayload.builder();
  }

  @Override
  protected void setValue(ObliterateOpResponsePayload.ObliterateOpResponsePayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    // No fields per KMIP spec
  }

  @Override
  protected ObliterateOpResponsePayload build(
      ObliterateOpResponsePayload.ObliterateOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}