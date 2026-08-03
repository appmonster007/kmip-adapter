package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.Attribute;
import org.purplebean.kmip.model.core.structure.Name;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;

public class TemplateAttributeTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<TemplateAttribute,
        TemplateAttribute.TemplateAttributeBuilder> {

  public TemplateAttributeTtlvDeserializer() {
    super(TemplateAttribute.kmipTag, TemplateAttribute.encodingType);
  }

  @Override
  protected TemplateAttribute.TemplateAttributeBuilder createBuilder() {
    return TemplateAttribute.builder();
  }

  @Override
  protected void setValue(TemplateAttribute.TemplateAttributeBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.NAME -> builder.name(mapper.readValue(p, Name.class));
      case KmipTag.Standard.ATTRIBUTE -> builder.attribute(mapper.readValue(p, Attribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected TemplateAttribute build(TemplateAttribute.TemplateAttributeBuilder builder) {
    return builder.build();
  }
}