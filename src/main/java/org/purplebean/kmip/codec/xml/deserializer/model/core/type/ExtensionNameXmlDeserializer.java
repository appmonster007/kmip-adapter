package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.ExtensionName;

/**
 * XML deserializer for {@link ExtensionName}.
 */
public class ExtensionNameXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<ExtensionName, ExtensionName.ExtensionNameBuilder> {

  /**
   * Constructs a new {@link ExtensionNameXmlDeserializer}.
   */
  public ExtensionNameXmlDeserializer() {
    super(ExtensionName.kmipTag, ExtensionName.encodingType);
  }

  @Override
  protected ExtensionName.ExtensionNameBuilder createBuilder() {
    return ExtensionName.builder();
  }

  @Override
  protected void setValue(ExtensionName.ExtensionNameBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, String.class));
  }

  @Override
  protected ExtensionName build(ExtensionName.ExtensionNameBuilder builder) {
    return builder.build();
  }
}