package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.LinkType;

public class LinkTypeJsonSerializer extends AbstractKmipJsonSerializer<LinkType, String> {

    public LinkTypeJsonSerializer() {
        super(LinkType::getDescription);
    }
}