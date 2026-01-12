package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.LinkType;

public class LinkTypeTtlvDeserializer extends AbstractKmipTtlvDeserializer<LinkType, Integer> {

    public LinkTypeTtlvDeserializer() {
        super(LinkType.kmipTag, LinkType.encodingType, Integer.class, value -> new LinkType(LinkType.fromValue(value)));
    }
}