package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.LinkType;

public class LinkTypeTtlvSerializer extends AbstractKmipTtlvSerializer<LinkType, Integer> {

    public LinkTypeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}