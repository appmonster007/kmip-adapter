package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.LinkType;

import java.io.IOException;
import java.nio.ByteBuffer;

public class LinkTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<LinkType, LinkType.LinkTypeBuilder> {

    public LinkTypeTtlvDeserializer() {
        super(LinkType.kmipTag, LinkType.encodingType);
    }

    @Override
    protected LinkType.LinkTypeBuilder createBuilder() {
        return LinkType.builder();
    }

    @Override
    protected void setValue(LinkType.LinkTypeBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(LinkType.fromValue(value));
    }

    @Override
    protected LinkType build(LinkType.LinkTypeBuilder builder) {
        return builder.build();
    }
}
