package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.ActivateOpResponsePayload;

/**
 * TTLV deserializer for {@link ActivateOpResponsePayload}.
 */
public class ActivateOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ActivateOpResponsePayload,
        ActivateOpResponsePayload.ActivateOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link ActivateOpResponsePayloadTtlvDeserializer}.
   */
  public ActivateOpResponsePayloadTtlvDeserializer() {
    super(ActivateOpResponsePayload.kmipTag, ActivateOpResponsePayload.encodingType);
  }

  @Override
  protected ActivateOpResponsePayload.ActivateOpResponsePayloadBuilder createBuilder() {
    return ActivateOpResponsePayload.builder();
  }

  @Override
  protected void setValue(ActivateOpResponsePayload.ActivateOpResponsePayloadBuilder builder,
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
  protected ActivateOpResponsePayload build(
      ActivateOpResponsePayload.ActivateOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
