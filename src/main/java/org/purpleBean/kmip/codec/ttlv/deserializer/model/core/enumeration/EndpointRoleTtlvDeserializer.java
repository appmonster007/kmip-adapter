package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.enumeration.EndpointRole;

public class EndpointRoleTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<EndpointRole, Integer> {

    public EndpointRoleTtlvDeserializer() {
        super(EndpointRole.kmipTag, EndpointRole.encodingType, Integer.class, value -> new EndpointRole(EndpointRole.fromValue(value)));
    }
}