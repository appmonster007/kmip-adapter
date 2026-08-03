package org.purpleBean.kmip.codec.xml.deserializer.model.v3x0.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v3x0.type.PasswordSaltAlgorithm;

public class PasswordSaltAlgorithmXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<PasswordSaltAlgorithm,
        PasswordSaltAlgorithm.PasswordSaltAlgorithmBuilder> {

  public PasswordSaltAlgorithmXmlDeserializer() {
    super(PasswordSaltAlgorithm.kmipTag, PasswordSaltAlgorithm.encodingType);
  }

  @Override
  protected PasswordSaltAlgorithm.PasswordSaltAlgorithmBuilder createBuilder() {
    return PasswordSaltAlgorithm.builder();
  }

  @Override
  protected void setValue(PasswordSaltAlgorithm.PasswordSaltAlgorithmBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(PasswordSaltAlgorithm.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected PasswordSaltAlgorithm build(
      PasswordSaltAlgorithm.PasswordSaltAlgorithmBuilder builder) {
    return builder.build();
  }
}