package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.Issuer;

public class IssuerJsonDeserializer
    extends AbstractKmipDataTypeJsonDeserializer<Issuer, Issuer.IssuerBuilder> {

  public IssuerJsonDeserializer() {
    super(Issuer.kmipTag, Issuer.encodingType);
  }

  @Override
  protected Issuer.IssuerBuilder createBuilder() {
    return Issuer.builder();
  }

  @Override
  protected void setValue(Issuer.IssuerBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected Issuer build(Issuer.IssuerBuilder builder) {
    return builder.build();
  }
}
