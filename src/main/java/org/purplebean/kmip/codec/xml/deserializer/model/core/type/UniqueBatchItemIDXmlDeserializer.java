package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.UniqueBatchItemID;

/**
 * XML deserializer for {@link UniqueBatchItemID}.
 */
public class UniqueBatchItemIDXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<UniqueBatchItemID,
        UniqueBatchItemID.UniqueBatchItemIDBuilder> {

  /**
   * Constructs a new {@link UniqueBatchItemIDXmlDeserializer}.
   */
  public UniqueBatchItemIDXmlDeserializer() {
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