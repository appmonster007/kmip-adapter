package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.Issuer;

/**
 * XML deserializer for {@link Issuer}.
 */
public class IssuerXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<Issuer, Issuer.IssuerBuilder> {

  /**
   * Constructs a new {@link IssuerXmlDeserializer}.
   */
  public IssuerXmlDeserializer() {
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