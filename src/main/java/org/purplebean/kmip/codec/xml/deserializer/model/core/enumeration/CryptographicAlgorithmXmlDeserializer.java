package org.purplebean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;

/**
 * XML deserializer for {@link CryptographicAlgorithm}.
 */
public class CryptographicAlgorithmXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CryptographicAlgorithm,
        CryptographicAlgorithm.CryptographicAlgorithmBuilder> {

  /**
   * Constructs a new {@link CryptographicAlgorithmXmlDeserializer}.
   */
  public CryptographicAlgorithmXmlDeserializer() {
    super(CryptographicAlgorithm.kmipTag, CryptographicAlgorithm.encodingType);
  }

  @Override
  protected CryptographicAlgorithm.CryptographicAlgorithmBuilder createBuilder() {
    return CryptographicAlgorithm.builder();
  }

  @Override
  protected void setValue(CryptographicAlgorithm.CryptographicAlgorithmBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(CryptographicAlgorithm.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected CryptographicAlgorithm build(
      CryptographicAlgorithm.CryptographicAlgorithmBuilder builder) {
    return builder.build();
  }
}