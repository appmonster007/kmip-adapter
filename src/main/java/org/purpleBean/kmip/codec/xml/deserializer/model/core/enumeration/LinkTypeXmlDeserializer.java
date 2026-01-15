package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.LinkType;

public class LinkTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<LinkType, String> {

    public LinkTypeXmlDeserializer() {
        super(LinkType.kmipTag, LinkType.encodingType, String.class, value -> new LinkType(LinkType.fromName(value)));
    }
}