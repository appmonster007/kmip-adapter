package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.enumeration.SecretDataType;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.JoinSplitKeyOpRequestPayload;

public class JoinSplitKeyOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<JoinSplitKeyOpRequestPayload,
        JoinSplitKeyOpRequestPayload.JoinSplitKeyOpRequestPayloadBuilder> {

  public JoinSplitKeyOpRequestPayloadTtlvDeserializer() {
    super(JoinSplitKeyOpRequestPayload.kmipTag, JoinSplitKeyOpRequestPayload.encodingType);
  }

  @Override
  protected JoinSplitKeyOpRequestPayload.JoinSplitKeyOpRequestPayloadBuilder createBuilder() {
    return JoinSplitKeyOpRequestPayload.builder();
  }

  @Override
  protected void setValue(JoinSplitKeyOpRequestPayload.JoinSplitKeyOpRequestPayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OBJECT_TYPE ->
          builder.objectType(mapper.readValue(p, ObjectType.class));
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.SECRET_DATA_TYPE ->
          builder.secretDataType(mapper.readValue(p, SecretDataType.class));
      case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
          builder.templateAttribute(mapper.readValue(p, TemplateAttribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected JoinSplitKeyOpRequestPayload build(
      JoinSplitKeyOpRequestPayload.JoinSplitKeyOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
