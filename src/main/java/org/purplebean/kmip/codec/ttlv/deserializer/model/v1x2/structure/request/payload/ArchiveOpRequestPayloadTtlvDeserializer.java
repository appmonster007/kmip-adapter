package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.ArchiveOpRequestPayload;

/**
 * TTLV deserializer for {@link ArchiveOpRequestPayload}.
 */
public class ArchiveOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ArchiveOpRequestPayload,
        ArchiveOpRequestPayload.ArchiveOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link ArchiveOpRequestPayloadTtlvDeserializer}.
   */
  public ArchiveOpRequestPayloadTtlvDeserializer() {
    super(ArchiveOpRequestPayload.kmipTag, ArchiveOpRequestPayload.encodingType);
  }

  @Override
  protected ArchiveOpRequestPayload.ArchiveOpRequestPayloadBuilder createBuilder() {
    return ArchiveOpRequestPayload.builder();
  }

  @Override
  protected void setValue(ArchiveOpRequestPayload.ArchiveOpRequestPayloadBuilder builder,
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
  protected ArchiveOpRequestPayload build(
      ArchiveOpRequestPayload.ArchiveOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
