package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.EndpointRole;

public class EndpointRoleTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<EndpointRole, Integer> {

    public EndpointRoleTtlvDeserializer() {
        super(EndpointRole.kmipTag, EndpointRole.encodingType, Integer.class, value -> new EndpointRole(EndpointRole.fromValue(value)));
    }
}