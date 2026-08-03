package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.RecoverOpResponsePayload;

public class RecoverOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<RecoverOpResponsePayload,
        RecoverOpResponsePayload.RecoverOpResponsePayloadBuilder> {

  public RecoverOpResponsePayloadTtlvDeserializer() {
    super(RecoverOpResponsePayload.kmipTag, RecoverOpResponsePayload.encodingType);
  }

  @Override
  protected RecoverOpResponsePayload.RecoverOpResponsePayloadBuilder createBuilder() {
    return RecoverOpResponsePayload.builder();
  }

  @Override
  protected void setValue(RecoverOpResponsePayload.RecoverOpResponsePayloadBuilder builder,
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
  protected RecoverOpResponsePayload build(
      RecoverOpResponsePayload.RecoverOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
