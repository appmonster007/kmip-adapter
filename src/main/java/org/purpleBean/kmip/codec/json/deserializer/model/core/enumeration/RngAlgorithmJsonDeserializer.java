package org.purplebean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.RngAlgorithm;

public class RngAlgorithmJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<RngAlgorithm, RngAlgorithm.RngAlgorithmBuilder> {

  public RngAlgorithmJsonDeserializer() {
    super(RngAlgorithm.kmipTag, RngAlgorithm.encodingType);
  }

  @Override
  protected RngAlgorithm.RngAlgorithmBuilder createBuilder() {
    return RngAlgorithm.builder();
  }

  @Override
  protected void setValue(RngAlgorithm.RngAlgorithmBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(RngAlgorithm.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected RngAlgorithm build(RngAlgorithm.RngAlgorithmBuilder builder) {
    return builder.build();
  }
}
