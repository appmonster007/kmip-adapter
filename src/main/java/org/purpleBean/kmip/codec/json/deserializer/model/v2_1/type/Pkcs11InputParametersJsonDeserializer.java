package org.purpleBean.kmip.codec.json.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2_1.type.Pkcs11InputParameters;

public class Pkcs11InputParametersJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<Pkcs11InputParameters,
        Pkcs11InputParameters.Pkcs11InputParametersBuilder> {

  public Pkcs11InputParametersJsonDeserializer() {
    super(Pkcs11InputParameters.kmipTag, Pkcs11InputParameters.encodingType);
  }

  @Override
  protected Pkcs11InputParameters.Pkcs11InputParametersBuilder createBuilder() {
    return Pkcs11InputParameters.builder();
  }

  @Override
  protected void setValue(Pkcs11InputParameters.Pkcs11InputParametersBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected Pkcs11InputParameters build(
      Pkcs11InputParameters.Pkcs11InputParametersBuilder builder) {
    return builder.build();
  }
}