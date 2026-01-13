package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.EndpointRole;

public class EndpointRoleJsonSerializer extends AbstractKmipDataTypeJsonSerializer<EndpointRole, String> {

    public EndpointRoleJsonSerializer() {
        super(EndpointRole::getDescription);
    }
}