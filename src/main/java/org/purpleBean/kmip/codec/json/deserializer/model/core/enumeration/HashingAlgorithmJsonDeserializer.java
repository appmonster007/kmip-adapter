package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.HashingAlgorithm;

public class HashingAlgorithmJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<HashingAlgorithm,
        HashingAlgorithm.HashingAlgorithmBuilder> {

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
