package org.purpleBean.kmip.codec.xml.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.enumeration.EndpointRole;

public class EndpointRoleXmlSerializer extends AbstractKmipDataTypeXmlSerializer<EndpointRole, String> {

    public EndpointRoleXmlSerializer() {
        super(EndpointRole::getDescription);
    }
}