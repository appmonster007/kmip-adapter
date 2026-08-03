package org.purplebean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.DigitalSignatureAlgorithm;

/**
 * XML deserializer for {@link DigitalSignatureAlgorithm}.
 */
public class DigitalSignatureAlgorithmXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<DigitalSignatureAlgorithm,
        DigitalSignatureAlgorithm.DigitalSignatureAlgorithmBuilder> {

  /**
   * Constructs a new {@link DigitalSignatureAlgorithmXmlDeserializer}.
   */
  public DigitalSignatureAlgorithmXmlDeserializer() {
    super(DigitalSignatureAlgorithm.kmipTag, DigitalSignatureAlgorithm.encodingType);
  }

  @Override
  protected DigitalSignatureAlgorithm.DigitalSignatureAlgorithmBuilder createBuilder() {
    return DigitalSignatureAlgorithm.builder();
  }

  @Override
  protected void setValue(DigitalSignatureAlgorithm.DigitalSignatureAlgorithmBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(DigitalSignatureAlgorithm.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected DigitalSignatureAlgorithm build(
      DigitalSignatureAlgorithm.DigitalSignatureAlgorithmBuilder builder) {
    return builder.build();
  }
}