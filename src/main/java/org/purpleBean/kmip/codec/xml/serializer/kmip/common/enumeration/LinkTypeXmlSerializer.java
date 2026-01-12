package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.LinkType;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class LinkTypeXmlSerializer extends AbstractKmipXmlSerializer<LinkType, String> {

    public LinkTypeXmlSerializer() {
        super(LinkType::getDescription);
    }
}