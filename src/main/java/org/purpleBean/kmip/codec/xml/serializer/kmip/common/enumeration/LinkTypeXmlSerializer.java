package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.enumeration.LinkType;

public class LinkTypeXmlSerializer extends AbstractKmipXmlSerializer<LinkType, String> {

    public LinkTypeXmlSerializer() {
        super(LinkType::getDescription);
    }
}