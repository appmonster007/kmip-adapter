package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.LinkType;

public class LinkTypeXmlSerializer extends AbstractKmipDataTypeXmlSerializer<LinkType, String> {

    public LinkTypeXmlSerializer() {
        super(LinkType::getDescription);
    }
}