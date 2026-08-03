package org.purpleBean.kmip.codec.json.deserializer.model.v3x0.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v3x0.type.PasswordSaltAlgorithm;

public class PasswordSaltAlgorithmJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<PasswordSaltAlgorithm,
        PasswordSaltAlgorithm.PasswordSaltAlgorithmBuilder> {

  public PasswordSaltAlgorithmJsonDeserializer() {
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