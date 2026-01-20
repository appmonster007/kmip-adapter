package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.LinkType;

public class LinkTypeTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<LinkType, Integer> {

    public LinkTypeTtlvDeserializer() {
        super(LinkType.kmipTag, LinkType.encodingType, Integer.class, value -> LinkType.fromValue(value).inst());
    }
}