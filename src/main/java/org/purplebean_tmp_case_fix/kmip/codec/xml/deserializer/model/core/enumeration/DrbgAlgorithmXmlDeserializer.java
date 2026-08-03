package org.purplebean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.DrbgAlgorithm;

public class DrbgAlgorithmXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<DrbgAlgorithm, DrbgAlgorithm.DrbgAlgorithmBuilder> {

  public DrbgAlgorithmXmlDeserializer() {
    super(DrbgAlgorithm.kmipTag, DrbgAlgorithm.encodingType);
  }

  @Override
  protected DrbgAlgorithm.DrbgAlgorithmBuilder createBuilder() {
    return DrbgAlgorithm.builder();
  }

  @Override
  protected void setValue(DrbgAlgorithm.DrbgAlgorithmBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(DrbgAlgorithm.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected DrbgAlgorithm build(DrbgAlgorithm.DrbgAlgorithmBuilder builder) {
    return builder.build();
  }
}