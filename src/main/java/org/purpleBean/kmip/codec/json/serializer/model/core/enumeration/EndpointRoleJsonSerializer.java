package org.purpleBean.kmip.codec.json.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.json.serializer.api.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.enumeration.EndpointRole;

public class EndpointRoleJsonSerializer extends AbstractKmipDataTypeJsonSerializer<EndpointRole, String> {

    public EndpointRoleJsonSerializer() {
        super(EndpointRole::getDescription);
    }
}