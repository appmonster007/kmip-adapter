package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2_1.structure.response.payload.SetDefaultsOpResponsePayload;

public class SetDefaultsOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SetDefaultsOpResponsePayload,
        SetDefaultsOpResponsePayload.SetDefaultsOpResponsePayloadBuilder> {

  public SetDefaultsOpResponsePayloadTtlvDeserializer() {
    super(SetDefaultsOpResponsePayload.kmipTag, SetDefaultsOpResponsePayload.encodingType);
  }

  @Override
  protected SetDefaultsOpResponsePayload.SetDefaultsOpResponsePayloadBuilder createBuilder() {
    return SetDefaultsOpResponsePayload.builder();
  }

  @Override
  protected void setValue(SetDefaultsOpResponsePayload.SetDefaultsOpResponsePayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    // No fields per KMIP spec
  }

  @Override
  protected SetDefaultsOpResponsePayload build(
      SetDefaultsOpResponsePayload.SetDefaultsOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}