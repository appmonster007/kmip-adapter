package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.enumeration.EndpointRole;

public class EndpointRoleTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<EndpointRole, Integer> {

    public EndpointRoleTtlvSerializer() {
        super(EndpointRole::getValue);
    }
}