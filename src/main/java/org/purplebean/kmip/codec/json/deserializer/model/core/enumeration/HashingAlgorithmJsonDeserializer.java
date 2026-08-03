package org.purplebean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.HashingAlgorithm;

/**
 * JSON deserializer for {@link HashingAlgorithm}.
 */
public class HashingAlgorithmJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<HashingAlgorithm,
        HashingAlgorithm.HashingAlgorithmBuilder> {

  /**
   * Constructs a new {@link HashingAlgorithmJsonDeserializer}.
   */
  public HashingAlgorithmJsonDeserializer() {
    super(HashingAlgorithm.kmipTag, HashingAlgorithm.encodingType);
  }

  @Override
  protected HashingAlgorithm.HashingAlgorithmBuilder createBuilder() {
    return HashingAlgorithm.builder();
  }

  @Override
  protected void setValue(HashingAlgorithm.HashingAlgorithmBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(HashingAlgorithm.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected HashingAlgorithm build(HashingAlgorithm.HashingAlgorithmBuilder builder) {
    return builder.build();
  }
}
