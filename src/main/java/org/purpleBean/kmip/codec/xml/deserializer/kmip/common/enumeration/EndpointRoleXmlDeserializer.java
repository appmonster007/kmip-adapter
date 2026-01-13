package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.EndpointRole;

public class EndpointRoleXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<EndpointRole, String> {

    public EndpointRoleXmlDeserializer() {
        super(EndpointRole.kmipTag, EndpointRole.encodingType, String.class, value -> new EndpointRole(EndpointRole.fromName(value)));
    }
}