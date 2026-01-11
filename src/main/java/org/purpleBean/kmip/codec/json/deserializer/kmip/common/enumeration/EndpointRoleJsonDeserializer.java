package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.EndpointRole;

public class EndpointRoleJsonDeserializer extends AbstractKmipJsonDeserializer<EndpointRole, String> {

    public EndpointRoleJsonDeserializer() {
        super(EndpointRole.kmipTag, EndpointRole.encodingType, String.class, value -> new EndpointRole(EndpointRole.fromName(value)));
    }
}