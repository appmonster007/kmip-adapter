package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.RecoverOpRequestPayload;

/**
 * TTLV deserializer for {@link RecoverOpRequestPayload}.
 */
public class RecoverOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<RecoverOpRequestPayload,
        RecoverOpRequestPayload.RecoverOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link RecoverOpRequestPayloadTtlvDeserializer}.
   */
  public RecoverOpRequestPayloadTtlvDeserializer() {
    super(RecoverOpRequestPayload.kmipTag, RecoverOpRequestPayload.encodingType);
  }

  @Override
  protected RecoverOpRequestPayload.RecoverOpRequestPayloadBuilder createBuilder() {
    return RecoverOpRequestPayload.builder();
  }

  @Override
  protected void setValue(RecoverOpRequestPayload.RecoverOpRequestPayloadBuilder builder,
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
  protected RecoverOpRequestPayload build(
      RecoverOpRequestPayload.RecoverOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
