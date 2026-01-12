package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.EndpointRole;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class EndpointRoleXmlSerializer extends AbstractKmipXmlSerializer<EndpointRole, String> {

    public EndpointRoleXmlSerializer() {
        super(EndpointRole::getDescription);
    }
}