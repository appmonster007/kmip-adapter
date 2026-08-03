package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.Pkcs11Interface;

/**
 * XML deserializer for {@link Pkcs11Interface}.
 */
public class Pkcs11InterfaceXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<Pkcs11Interface, Pkcs11Interface.Pkcs11InterfaceBuilder> {

  /**
   * Constructs a new {@link Pkcs11InterfaceXmlDeserializer}.
   */
  public Pkcs11InterfaceXmlDeserializer() {
    super(Pkcs11Interface.kmipTag, Pkcs11Interface.encodingType);
  }

  @Override
  protected Pkcs11Interface.Pkcs11InterfaceBuilder createBuilder() {
    return Pkcs11Interface.builder();
  }

  @Override
  protected void setValue(Pkcs11Interface.Pkcs11InterfaceBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected Pkcs11Interface build(Pkcs11Interface.Pkcs11InterfaceBuilder builder) {
    return builder.build();
  }
}