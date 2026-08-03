package org.purplebean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.Fips186Variation;

/**
 * XML deserializer for {@link Fips186Variation}.
 */
public class Fips186VariationXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<Fips186Variation,
        Fips186Variation.Fips186VariationBuilder> {

  /**
   * Constructs a new {@link Fips186VariationXmlDeserializer}.
   */
  public Fips186VariationXmlDeserializer() {
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