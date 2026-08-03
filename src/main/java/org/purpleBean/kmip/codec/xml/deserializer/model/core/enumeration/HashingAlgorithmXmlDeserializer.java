package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.HashingAlgorithm;

public class HashingAlgorithmXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<HashingAlgorithm,
        HashingAlgorithm.HashingAlgorithmBuilder> {

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