package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.type.Pkcs11ReturnCode;

public class Pkcs11ReturnCodeXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<Pkcs11ReturnCode,
        Pkcs11ReturnCode.Pkcs11ReturnCodeBuilder> {

  public Pkcs11ReturnCodeXmlDeserializer() {
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