package org.purplebean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.type.Pkcs12FriendlyName;

public class Pkcs12FriendlyNameJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<Pkcs12FriendlyName,
        Pkcs12FriendlyName.Pkcs12FriendlyNameBuilder> {

  public Pkcs12FriendlyNameJsonDeserializer() {
    super(Pkcs12FriendlyName.kmipTag, Pkcs12FriendlyName.encodingType);
  }

  @Override
  protected Pkcs12FriendlyName.Pkcs12FriendlyNameBuilder createBuilder() {
    return Pkcs12FriendlyName.builder();
  }

  @Override
  protected void setValue(Pkcs12FriendlyName.Pkcs12FriendlyNameBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected Pkcs12FriendlyName build(Pkcs12FriendlyName.Pkcs12FriendlyNameBuilder builder) {
    return builder.build();
  }
}