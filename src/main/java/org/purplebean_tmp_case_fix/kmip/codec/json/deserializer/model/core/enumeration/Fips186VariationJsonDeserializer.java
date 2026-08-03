package org.purplebean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.Fips186Variation;

public class Fips186VariationJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<Fips186Variation,
        Fips186Variation.Fips186VariationBuilder> {

  public Fips186VariationJsonDeserializer() {
    super(Fips186Variation.kmipTag, Fips186Variation.encodingType);
  }

  @Override
  protected Fips186Variation.Fips186VariationBuilder createBuilder() {
    return Fips186Variation.builder();
  }

  @Override
  protected void setValue(Fips186Variation.Fips186VariationBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(Fips186Variation.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected Fips186Variation build(Fips186Variation.Fips186VariationBuilder builder) {
    return builder.build();
  }
}
