package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.EndpointRole;

public class EndpointRoleXmlSerializer extends AbstractKmipDataTypeXmlSerializer<EndpointRole, String> {

    public EndpointRoleXmlSerializer() {
        super(EndpointRole::getDescription);
    }
}