package org.purplebean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.ShreddingAlgorithm;

public class ShreddingAlgorithmXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ShreddingAlgorithm,
        ShreddingAlgorithm.ShreddingAlgorithmBuilder> {

  public ShreddingAlgorithmXmlDeserializer() {
    super(ShreddingAlgorithm.kmipTag, ShreddingAlgorithm.encodingType);
  }

  @Override
  protected ShreddingAlgorithm.ShreddingAlgorithmBuilder createBuilder() {
    return ShreddingAlgorithm.builder();
  }

  @Override
  protected void setValue(ShreddingAlgorithm.ShreddingAlgorithmBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ShreddingAlgorithm.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected ShreddingAlgorithm build(ShreddingAlgorithm.ShreddingAlgorithmBuilder builder) {
    return builder.build();
  }
}