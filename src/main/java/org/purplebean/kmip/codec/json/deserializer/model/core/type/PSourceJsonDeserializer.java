package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.PSource;

/**
 * JSON deserializer for {@link PSource}.
 */
public class PSourceJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<PSource, PSource.PSourceBuilder> {

  /**
   * Constructs a new {@link PSourceJsonDeserializer}.
   */
  public PSourceJsonDeserializer() {
    super(PSource.kmipTag, PSource.encodingType);
  }

  @Override
  protected PSource.PSourceBuilder createBuilder() {
    return PSource.builder();
  }

  @Override
  protected void setValue(PSource.PSourceBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected PSource build(PSource.PSourceBuilder builder) {
    return builder.build();
  }
}
