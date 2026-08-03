package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.MessageExtension;
import org.purplebean.kmip.model.core.structure.VendorExtension;
import org.purplebean.kmip.model.core.type.CriticalityIndicator;
import org.purplebean.kmip.model.core.type.VendorIdentification;

/**
 * TTLV deserializer for {@link MessageExtension}.
 */
public class MessageExtensionTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<MessageExtension,
        MessageExtension.MessageExtensionBuilder> {

  /**
   * Constructs a new {@link MessageExtensionTtlvDeserializer}.
   */
  public MessageExtensionTtlvDeserializer() {
    super(MessageExtension.kmipTag, MessageExtension.encodingType);
  }

  @Override
  protected MessageExtension.MessageExtensionBuilder createBuilder() {
    return MessageExtension.builder();
  }

  @Override
  protected void setValue(MessageExtension.MessageExtensionBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.VENDOR_IDENTIFICATION ->
          builder.vendorIdentification(mapper.readValue(p, VendorIdentification.class));
      case KmipTag.Standard.CRITICALITY_INDICATOR ->
          builder.criticalityIndicator(mapper.readValue(p, CriticalityIndicator.class));
      case KmipTag.Standard.VENDOR_EXTENSION ->
          builder.vendorExtension(mapper.readValue(p, VendorExtension.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected MessageExtension build(MessageExtension.MessageExtensionBuilder builder) {
    return builder.build();
  }
}