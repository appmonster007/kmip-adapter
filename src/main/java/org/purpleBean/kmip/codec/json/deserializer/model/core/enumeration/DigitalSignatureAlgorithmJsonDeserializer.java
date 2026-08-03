package org.purpleBean.kmip.codec.json.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.DigitalSignatureAlgorithm;

public class DigitalSignatureAlgorithmJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<DigitalSignatureAlgorithm,
        DigitalSignatureAlgorithm.DigitalSignatureAlgorithmBuilder> {

  public DigitalSignatureAlgorithmJsonDeserializer() {
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
