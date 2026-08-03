package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.ApplicationNamespace;

public class ApplicationNamespaceXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ApplicationNamespace,
        ApplicationNamespace.ApplicationNamespaceBuilder> {

  public ApplicationNamespaceXmlDeserializer() {
    super(ApplicationNamespace.kmipTag, ApplicationNamespace.encodingType);
  }

  @Override
  protected ApplicationNamespace.ApplicationNamespaceBuilder createBuilder() {
    return ApplicationNamespace.builder();
  }

  @Override
  protected void setValue(ApplicationNamespace.ApplicationNamespaceBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected ApplicationNamespace build(ApplicationNamespace.ApplicationNamespaceBuilder builder) {
    return builder.build();
  }
}