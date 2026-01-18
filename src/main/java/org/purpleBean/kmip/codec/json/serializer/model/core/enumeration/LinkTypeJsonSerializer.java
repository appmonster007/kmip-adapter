package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.LinkType;

public class LinkTypeJsonSerializer extends AbstractKmipDataTypeJsonSerializer<LinkType, String> {

    public LinkTypeJsonSerializer() {
        super(LinkType::getDescription);
    }
}