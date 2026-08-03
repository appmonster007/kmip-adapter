package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.math.BigInteger;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.J;

public class JXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<J, J.JBuilder> {

  public JXmlDeserializer() {
    super(J.kmipTag, J.encodingType);
  }

  @Override
  protected J.JBuilder createBuilder() {
    return J.builder();
  }

  @Override
  protected void setValue(J.JBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, BigInteger.class));
  }

  @Override
  protected J build(J.JBuilder builder) {
    return builder.build();
  }
}