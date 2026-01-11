package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.LinkType;

public class LinkTypeJsonDeserializer extends AbstractKmipJsonDeserializer<LinkType, String> {

    public LinkTypeJsonDeserializer() {
        super(LinkType.kmipTag, LinkType.encodingType, String.class, value -> new LinkType(LinkType.fromName(value)));
    }
}