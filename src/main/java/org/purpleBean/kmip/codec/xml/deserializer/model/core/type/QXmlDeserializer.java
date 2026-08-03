package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.math.BigInteger;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.Q;

public class QXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<Q, Q.QBuilder> {

  public QXmlDeserializer() {
    super(Q.kmipTag, Q.encodingType);
  }

  @Override
  protected Q.QBuilder createBuilder() {
    return Q.builder();
  }

  @Override
  protected void setValue(Q.QBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, BigInteger.class));
  }

  @Override
  protected Q build(Q.QBuilder builder) {
    return builder.build();
  }
}