package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1x2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.v1x2.structure.request.payload.CreateOpRequestPayload;

public class CreateOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CreateOpRequestPayload,
        CreateOpRequestPayload.CreateOpRequestPayloadBuilder> {

  public CreateOpRequestPayloadTtlvDeserializer() {
    super(CreateOpRequestPayload.kmipTag, CreateOpRequestPayload.encodingType);
  }

  @Override
  protected CreateOpRequestPayload.CreateOpRequestPayloadBuilder createBuilder() {
    return CreateOpRequestPayload.builder();
  }

  @Override
  protected void setValue(CreateOpRequestPayload.CreateOpRequestPayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OBJECT_TYPE ->
          builder.objectType(mapper.readValue(p, ObjectType.class));
      case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
          builder.templateAttribute(mapper.readValue(p, TemplateAttribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CreateOpRequestPayload build(
      CreateOpRequestPayload.CreateOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
