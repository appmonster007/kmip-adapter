package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.Pkcs11OutputParameters;

public class Pkcs11OutputParametersXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<Pkcs11OutputParameters,
        Pkcs11OutputParameters.Pkcs11OutputParametersBuilder> {

  public Pkcs11OutputParametersXmlDeserializer() {
    super(Pkcs11OutputParameters.kmipTag, Pkcs11OutputParameters.encodingType);
  }

  @Override
  protected Pkcs11OutputParameters.Pkcs11OutputParametersBuilder createBuilder() {
    return Pkcs11OutputParameters.builder();
  }

  @Override
  protected void setValue(Pkcs11OutputParameters.Pkcs11OutputParametersBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected Pkcs11OutputParameters build(
      Pkcs11OutputParameters.Pkcs11OutputParametersBuilder builder) {
    return builder.build();
  }
}