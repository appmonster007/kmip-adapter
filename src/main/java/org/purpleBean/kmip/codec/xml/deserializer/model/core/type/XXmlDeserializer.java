package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.math.BigInteger;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.X;

public class XXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<X, X.XBuilder> {

  public XXmlDeserializer() {
    super(X.kmipTag, X.encodingType);
  }

  @Override
  protected X.XBuilder createBuilder() {
    return X.builder();
  }

  @Override
  protected void setValue(X.XBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, BigInteger.class));
  }

  @Override
  protected X build(X.XBuilder builder) {
    return builder.build();
  }
}