package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.EndpointRole;

public class EndpointRoleXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<EndpointRole, String> {

    public EndpointRoleXmlDeserializer() {
        super(EndpointRole.kmipTag, EndpointRole.encodingType, String.class, value -> new EndpointRole(EndpointRole.fromName(value)));
    }
}