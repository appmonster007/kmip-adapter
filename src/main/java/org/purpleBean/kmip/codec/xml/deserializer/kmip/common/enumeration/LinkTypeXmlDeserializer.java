package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.LinkType;

public class LinkTypeXmlDeserializer extends AbstractKmipXmlDeserializer<LinkType, String> {

    public LinkTypeXmlDeserializer() {
        super(LinkType.kmipTag, LinkType.encodingType, String.class, value -> new LinkType(LinkType.fromName(value)));
    }
}