package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipAttribute;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.structure.NewAttribute;

/**
 * XML deserializer for {@link NewAttribute}.
 */
public class NewAttributeXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<NewAttribute, NewAttribute.NewAttributeBuilder> {

  /**
   * Constructs a new {@link NewAttributeXmlDeserializer}.
   */
  public NewAttributeXmlDeserializer() {
    super(NewAttribute.kmipTag, NewAttribute.encodingType);
  }

  @Override
  protected NewAttribute.NewAttributeBuilder createBuilder() {
    return NewAttribute.builder();
  }

  @Override
  protected void setValue(NewAttribute.NewAttributeBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.attribute(ctxt.readValue(p, KmipAttribute.class));
  }

  @Override
  protected NewAttribute build(NewAttribute.NewAttributeBuilder builder) {
    return builder.build();
  }
}
