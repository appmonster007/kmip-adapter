package org.purplebean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.DerivationMethod;

public class DerivationMethodXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<DerivationMethod,
        DerivationMethod.DerivationMethodBuilder> {

  public DerivationMethodXmlDeserializer() {
    super(DerivationMethod.kmipTag, DerivationMethod.encodingType);
  }

  @Override
  protected DerivationMethod.DerivationMethodBuilder createBuilder() {
    return DerivationMethod.builder();
  }

  @Override
  protected void setValue(DerivationMethod.DerivationMethodBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(DerivationMethod.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected DerivationMethod build(DerivationMethod.DerivationMethodBuilder builder) {
    return builder.build();
  }
}