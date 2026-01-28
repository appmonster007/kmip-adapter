package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.MessageExtension;
import org.purpleBean.kmip.model.core.structure.VendorExtension;
import org.purpleBean.kmip.model.core.type.CriticalityIndicator;
import org.purpleBean.kmip.model.core.type.VendorIdentification;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MessageExtensionTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<MessageExtension, MessageExtension.MessageExtensionBuilder> {

    public MessageExtensionTtlvDeserializer() {
        super(MessageExtension.kmipTag);
    }

    @Override
    protected MessageExtension.MessageExtensionBuilder createBuilder() {
        return MessageExtension.builder();
    }

    @Override
    protected void setValue(MessageExtension.MessageExtensionBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
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

    @Override
    protected EncodingType getEncodingType() {
        return MessageExtension.encodingType;
    }
}