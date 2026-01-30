package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.MessageExtension;
import org.purpleBean.kmip.model.core.structure.VendorExtension;
import org.purpleBean.kmip.model.core.type.CriticalityIndicator;
import org.purpleBean.kmip.model.core.type.VendorIdentification;

import java.io.IOException;

public class MessageExtensionJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<MessageExtension, MessageExtension.MessageExtensionBuilder> {

    public MessageExtensionJsonDeserializer() {
        super(MessageExtension.kmipTag, MessageExtension.encodingType);
    }

    @Override
    protected MessageExtension.MessageExtensionBuilder createBuilder() {
        return MessageExtension.builder();
    }

    @Override
    protected void setValue(MessageExtension.MessageExtensionBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.VENDOR_IDENTIFICATION ->
                    builder.vendorIdentification(ctxt.readValue(p, VendorIdentification.class));
            case KmipTag.Standard.CRITICALITY_INDICATOR ->
                    builder.criticalityIndicator(ctxt.readValue(p, CriticalityIndicator.class));
            case KmipTag.Standard.VENDOR_EXTENSION -> builder.vendorExtension(ctxt.readValue(p, VendorExtension.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected MessageExtension build(MessageExtension.MessageExtensionBuilder builder) {
        return builder.build();
    }
}
