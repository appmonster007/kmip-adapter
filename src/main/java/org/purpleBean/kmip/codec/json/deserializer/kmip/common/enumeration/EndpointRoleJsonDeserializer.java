package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.EndpointRole;

public class EndpointRoleJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<EndpointRole, String> {

    public EndpointRoleJsonDeserializer() {
        super(EndpointRole.kmipTag, EndpointRole.encodingType, String.class, value -> new EndpointRole(EndpointRole.fromName(value)));
    }
}