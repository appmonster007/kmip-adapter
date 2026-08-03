package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.response.payload.ImportOpResponsePayload;

/**
 * TTLV deserializer for {@link ImportOpResponsePayload}.
 */
public class ImportOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ImportOpResponsePayload,
        ImportOpResponsePayload.ImportOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link ImportOpResponsePayloadTtlvDeserializer}.
   */
  public ImportOpResponsePayloadTtlvDeserializer() {
    super(ImportOpResponsePayload.kmipTag, ImportOpResponsePayload.encodingType);
  }

  @Override
  protected ImportOpResponsePayload.ImportOpResponsePayloadBuilder createBuilder() {
    return ImportOpResponsePayload.builder();
  }

  @Override
  protected void setValue(ImportOpResponsePayload.ImportOpResponsePayloadBuilder builder,
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
  protected ImportOpResponsePayload build(
      ImportOpResponsePayload.ImportOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}