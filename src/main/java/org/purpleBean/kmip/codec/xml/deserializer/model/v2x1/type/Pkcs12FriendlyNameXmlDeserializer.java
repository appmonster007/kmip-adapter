package org.purpleBean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2x1.type.Pkcs12FriendlyName;

public class Pkcs12FriendlyNameXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<Pkcs12FriendlyName,
        Pkcs12FriendlyName.Pkcs12FriendlyNameBuilder> {

  public Pkcs12FriendlyNameXmlDeserializer() {
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