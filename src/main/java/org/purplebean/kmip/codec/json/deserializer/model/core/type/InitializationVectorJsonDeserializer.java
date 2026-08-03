package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.InitializationVector;

/**
 * JSON deserializer for {@link InitializationVector}.
 */
public class InitializationVectorJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<InitializationVector,
        InitializationVector.InitializationVectorBuilder> {

  /**
   * Constructs a new {@link InitializationVectorJsonDeserializer}.
   */
  public InitializationVectorJsonDeserializer() {
    super(InitializationVector.kmipTag, InitializationVector.encodingType);
  }

  @Override
  protected InitializationVector.InitializationVectorBuilder createBuilder() {
    return InitializationVector.builder();
  }

  @Override
  protected void setValue(InitializationVector.InitializationVectorBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected InitializationVector build(InitializationVector.InitializationVectorBuilder builder) {
    return builder.build();
  }
}
