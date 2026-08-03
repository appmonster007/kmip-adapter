package org.purplebean.kmip.codec.json.deserializer.model.v1x2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.ArchiveOpRequestPayload;

/**
 * JSON deserializer for {@link ArchiveOpRequestPayload}.
 */
public class ArchiveOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ArchiveOpRequestPayload,
        ArchiveOpRequestPayload.ArchiveOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link ArchiveOpRequestPayloadJsonDeserializer}.
   */
  public ArchiveOpRequestPayloadJsonDeserializer() {
    super(ArchiveOpRequestPayload.kmipTag, ArchiveOpRequestPayload.encodingType);
  }

  @Override
  protected ArchiveOpRequestPayload.ArchiveOpRequestPayloadBuilder createBuilder() {
    return ArchiveOpRequestPayload.builder();
  }

  @Override
  protected void setValue(ArchiveOpRequestPayload.ArchiveOpRequestPayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    if (nodeTag.equals(KmipTag.Standard.UNIQUE_IDENTIFIER)) {
      builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
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
