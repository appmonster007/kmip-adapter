package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;

public class CryptographicAlgorithmJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CryptographicAlgorithm,
        CryptographicAlgorithm.CryptographicAlgorithmBuilder> {

  public CryptographicAlgorithmJsonDeserializer() {
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
