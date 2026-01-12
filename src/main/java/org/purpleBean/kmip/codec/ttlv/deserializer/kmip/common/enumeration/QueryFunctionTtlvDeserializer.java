package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.enumeration.QueryFunction;

public class QueryFunctionTtlvDeserializer extends AbstractKmipTtlvDeserializer<QueryFunction, Integer> {

    public QueryFunctionTtlvDeserializer() {
        super(QueryFunction.kmipTag, QueryFunction.encodingType, Integer.class, value -> new QueryFunction(QueryFunction.fromValue(value)));
    }
}