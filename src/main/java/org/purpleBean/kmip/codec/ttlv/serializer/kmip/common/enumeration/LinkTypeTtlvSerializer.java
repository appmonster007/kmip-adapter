package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.LinkType;

public class LinkTypeTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<LinkType, Integer> {

    public LinkTypeTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}