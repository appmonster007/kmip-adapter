package org.purplebean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.HashingAlgorithm;

/**
 * XML deserializer for {@link HashingAlgorithm}.
 */
public class HashingAlgorithmXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<HashingAlgorithm,
        HashingAlgorithm.HashingAlgorithmBuilder> {

  /**
   * Constructs a new {@link HashingAlgorithmXmlDeserializer}.
   */
  public HashingAlgorithmXmlDeserializer() {
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