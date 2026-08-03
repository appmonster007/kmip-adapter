package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.ActivateOpRequestPayload;

/**
 * TTLV deserializer for {@link ActivateOpRequestPayload}.
 */
public class ActivateOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ActivateOpRequestPayload,
        ActivateOpRequestPayload.ActivateOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link ActivateOpRequestPayloadTtlvDeserializer}.
   */
  public ActivateOpRequestPayloadTtlvDeserializer() {
    super(ActivateOpRequestPayload.kmipTag, ActivateOpRequestPayload.encodingType);
  }

  @Override
  protected ActivateOpRequestPayload.ActivateOpRequestPayloadBuilder createBuilder() {
    return ActivateOpRequestPayload.builder();
  }

  @Override
  protected void setValue(ActivateOpRequestPayload.ActivateOpRequestPayloadBuilder builder,
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
  protected ActivateOpRequestPayload build(
      ActivateOpRequestPayload.ActivateOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
