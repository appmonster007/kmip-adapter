package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.AsynchronousIndicator;

/**
 * JSON deserializer for {@link AsynchronousIndicator}.
 */
public class AsynchronousIndicatorJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<AsynchronousIndicator,
        AsynchronousIndicator.AsynchronousIndicatorBuilder> {

  /**
   * Constructs a new {@link AsynchronousIndicatorJsonDeserializer}.
   */
  public AsynchronousIndicatorJsonDeserializer() {
    super(AsynchronousIndicator.kmipTag, AsynchronousIndicator.encodingType);
  }

  @Override
  protected AsynchronousIndicator.AsynchronousIndicatorBuilder createBuilder() {
    return AsynchronousIndicator.builder();
  }

  @Override
  protected void setValue(AsynchronousIndicator.AsynchronousIndicatorBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, Boolean.class));
  }

  @Override
  protected AsynchronousIndicator build(
      AsynchronousIndicator.AsynchronousIndicatorBuilder builder) {
    return builder.build();
  }
}
