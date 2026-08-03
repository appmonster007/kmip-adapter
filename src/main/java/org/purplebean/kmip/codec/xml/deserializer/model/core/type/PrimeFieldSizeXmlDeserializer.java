package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.math.BigInteger;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.PrimeFieldSize;

/**
 * XML deserializer for {@link PrimeFieldSize}.
 */
public class PrimeFieldSizeXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<PrimeFieldSize, PrimeFieldSize.PrimeFieldSizeBuilder> {

  /**
   * Constructs a new {@link PrimeFieldSizeXmlDeserializer}.
   */
  public PrimeFieldSizeXmlDeserializer() {
    super(PrimeFieldSize.kmipTag, PrimeFieldSize.encodingType);
  }

  @Override
  protected PrimeFieldSize.PrimeFieldSizeBuilder createBuilder() {
    return PrimeFieldSize.builder();
  }

  @Override
  protected void setValue(PrimeFieldSize.PrimeFieldSizeBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, BigInteger.class));
  }

  @Override
  protected PrimeFieldSize build(PrimeFieldSize.PrimeFieldSizeBuilder builder) {
    return builder.build();
  }
}