package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.UniqueBatchItemID;

/**
 * JSON deserializer for {@link UniqueBatchItemID}.
 */
public class UniqueBatchItemIDJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<UniqueBatchItemID,
        UniqueBatchItemID.UniqueBatchItemIDBuilder> {

  /**
   * Constructs a new {@link UniqueBatchItemIDJsonDeserializer}.
   */
  public UniqueBatchItemIDJsonDeserializer() {
    super(UniqueBatchItemID.kmipTag, UniqueBatchItemID.encodingType);
  }

  @Override
  protected UniqueBatchItemID.UniqueBatchItemIDBuilder createBuilder() {
    return UniqueBatchItemID.builder();
  }

  @Override
  protected void setValue(UniqueBatchItemID.UniqueBatchItemIDBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected UniqueBatchItemID build(UniqueBatchItemID.UniqueBatchItemIDBuilder builder) {
    return builder.build();
  }
}
