package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.EndpointRole;

public class EndpointRoleTtlvSerializer extends AbstractKmipTtlvSerializer<EndpointRole, Integer> {

    public EndpointRoleTtlvSerializer() {
        super(value -> value.getValue().getValue());
    }
}