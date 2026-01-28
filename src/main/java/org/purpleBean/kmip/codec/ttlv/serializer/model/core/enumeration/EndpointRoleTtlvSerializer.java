package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.EndpointRole;

public class EndpointRoleTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<EndpointRole, Integer> {

    public EndpointRoleTtlvSerializer() {
        super(EndpointRole::getIntValue);
    }
}