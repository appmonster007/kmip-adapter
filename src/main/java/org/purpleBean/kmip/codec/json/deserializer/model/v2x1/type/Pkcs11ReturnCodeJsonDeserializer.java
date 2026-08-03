package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.Pkcs11ReturnCode;

public class Pkcs11ReturnCodeJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<Pkcs11ReturnCode,
        Pkcs11ReturnCode.Pkcs11ReturnCodeBuilder> {

  public Pkcs11ReturnCodeJsonDeserializer() {
    super(Pkcs11ReturnCode.kmipTag, Pkcs11ReturnCode.encodingType);
  }

  @Override
  protected Pkcs11ReturnCode.Pkcs11ReturnCodeBuilder createBuilder() {
    return Pkcs11ReturnCode.builder();
  }

  @Override
  protected void setValue(Pkcs11ReturnCode.Pkcs11ReturnCodeBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, Integer.class));
  }

  @Override
  protected Pkcs11ReturnCode build(Pkcs11ReturnCode.Pkcs11ReturnCodeBuilder builder) {
    return builder.build();
  }
}