package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.EndpointRole;

public class EndpointRoleTtlvDeserializer extends AbstractKmipTtlvDeserializer<EndpointRole, Integer> {

    public EndpointRoleTtlvDeserializer() {
        super(EndpointRole.kmipTag, EndpointRole.encodingType, Integer.class, value -> new EndpointRole(EndpointRole.fromValue(value)));
    }
}