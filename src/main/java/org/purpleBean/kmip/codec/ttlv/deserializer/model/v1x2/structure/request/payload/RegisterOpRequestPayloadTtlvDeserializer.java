package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1x2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.ManagedObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.v1x2.structure.request.payload.RegisterOpRequestPayload;

public class RegisterOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<RegisterOpRequestPayload,
        RegisterOpRequestPayload.RegisterOpRequestPayloadBuilder> {

  public RegisterOpRequestPayloadTtlvDeserializer() {
    super(RegisterOpRequestPayload.kmipTag, RegisterOpRequestPayload.encodingType);
  }

  @Override
  protected RegisterOpRequestPayload.RegisterOpRequestPayloadBuilder createBuilder() {
    return RegisterOpRequestPayload.builder();
  }

  @Override
  protected void setValue(RegisterOpRequestPayload.RegisterOpRequestPayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OBJECT_TYPE -> {
        ObjectType objectType = mapper.readValue(p, ObjectType.class);
        mapper.setAttribute("objectType", objectType.getDescription());
        builder.objectType(objectType);
      }
      case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
          builder.templateAttribute(mapper.readValue(p, TemplateAttribute.class));
      default -> {
        if (ManagedObject.isManagedObject(nodeTag)) {
          builder.object(mapper.readValue(p, ManagedObject.class));
        } else {
          throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
      }
    }
  }

  @Override
  protected RegisterOpRequestPayload build(
      RegisterOpRequestPayload.RegisterOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
