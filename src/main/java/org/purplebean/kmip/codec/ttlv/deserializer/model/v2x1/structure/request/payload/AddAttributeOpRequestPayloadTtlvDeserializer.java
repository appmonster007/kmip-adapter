package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v2x1.structure.NewAttribute;
import org.purplebean.kmip.model.v2x1.structure.request.payload.AddAttributeOpRequestPayload;

/**
 * TTLV deserializer for {@link AddAttributeOpRequestPayload}.
 */
public class AddAttributeOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AddAttributeOpRequestPayload,
        AddAttributeOpRequestPayload.AddAttributeOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link AddAttributeOpRequestPayloadTtlvDeserializer}.
   */
  public AddAttributeOpRequestPayloadTtlvDeserializer() {
    super(AddAttributeOpRequestPayload.kmipTag, AddAttributeOpRequestPayload.encodingType);
  }

  @Override
  protected AddAttributeOpRequestPayload.AddAttributeOpRequestPayloadBuilder createBuilder() {
    return AddAttributeOpRequestPayload.builder();
  }

  @Override
  protected void setValue(AddAttributeOpRequestPayload.AddAttributeOpRequestPayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.NEW_ATTRIBUTE ->
          builder.newAttribute(mapper.readValue(p, NewAttribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected AddAttributeOpRequestPayload build(
      AddAttributeOpRequestPayload.AddAttributeOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}