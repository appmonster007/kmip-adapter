package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.EndpointRole;

public class EndpointRoleJsonSerializer extends AbstractKmipJsonSerializer<EndpointRole, String> {

    public EndpointRoleJsonSerializer() {
        super(EndpointRole::getDescription);
    }
}