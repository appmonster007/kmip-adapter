package org.purpleBean.kmip.codec.ttlv.serializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.enumeration.QueryFunction;

public class QueryFunctionTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<QueryFunction, Integer> {

    public QueryFunctionTtlvSerializer() {
        super(QueryFunction::getValue);
    }
}