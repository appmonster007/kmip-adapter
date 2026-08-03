package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1x2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.ManagedObject;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.PutFunction;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.ReplacedUniqueIdentifier;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1x2.structure.request.payload.PutOpRequestPayload;

public class PutOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<PutOpRequestPayload,
        PutOpRequestPayload.PutOpRequestPayloadBuilder> {

  public PutOpRequestPayloadTtlvDeserializer() {
    super(PutOpRequestPayload.kmipTag, PutOpRequestPayload.encodingType);
  }

  @Override
  protected PutOpRequestPayload.PutOpRequestPayloadBuilder createBuilder() {
    return PutOpRequestPayload.builder();
  }

  @Override
  protected void setValue(PutOpRequestPayload.PutOpRequestPayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.PUT_FUNCTION ->
          builder.putFunction(mapper.readValue(p, PutFunction.class));
      case KmipTag.Standard.REPLACED_UNIQUE_IDENTIFIER ->
          builder.replacedUniqueIdentifier(mapper.readValue(p, ReplacedUniqueIdentifier.class));
      case KmipTag.Standard.ATTRIBUTE -> builder.attribute(mapper.readValue(p, Attribute.class));
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
  protected PutOpRequestPayload build(PutOpRequestPayload.PutOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
