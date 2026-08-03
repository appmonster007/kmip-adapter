package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.math.BigInteger;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.PrivateExponent;

public class PrivateExponentXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<PrivateExponent, PrivateExponent.PrivateExponentBuilder> {

  public PrivateExponentXmlDeserializer() {
    super(PrivateExponent.kmipTag, PrivateExponent.encodingType);
  }

  @Override
  protected PrivateExponent.PrivateExponentBuilder createBuilder() {
    return PrivateExponent.builder();
  }

  @Override
  protected void setValue(PrivateExponent.PrivateExponentBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, BigInteger.class));
  }

  @Override
  protected PrivateExponent build(PrivateExponent.PrivateExponentBuilder builder) {
    return builder.build();
  }
}