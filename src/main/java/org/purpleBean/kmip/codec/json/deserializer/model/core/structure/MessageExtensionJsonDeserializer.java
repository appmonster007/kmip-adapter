package org.purplebean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.structure.MessageExtension;
import org.purplebean.kmip.model.core.structure.VendorExtension;
import org.purplebean.kmip.model.core.type.CriticalityIndicator;
import org.purplebean.kmip.model.core.type.VendorIdentification;

public class MessageExtensionJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<MessageExtension,
        MessageExtension.MessageExtensionBuilder> {

  public MessageExtensionJsonDeserializer() {
    super(MessageExtension.kmipTag, MessageExtension.encodingType);
  }

  @Override
  protected MessageExtension.MessageExtensionBuilder createBuilder() {
    return MessageExtension.builder();
  }

  @Override
  protected void setValue(MessageExtension.MessageExtensionBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.VENDOR_IDENTIFICATION ->
          builder.vendorIdentification(ctxt.readValue(p, VendorIdentification.class));
      case KmipTag.Standard.CRITICALITY_INDICATOR ->
          builder.criticalityIndicator(ctxt.readValue(p, CriticalityIndicator.class));
      case KmipTag.Standard.VENDOR_EXTENSION ->
          builder.vendorExtension(ctxt.readValue(p, VendorExtension.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected MessageExtension build(MessageExtension.MessageExtensionBuilder builder) {
    return builder.build();
  }
}
