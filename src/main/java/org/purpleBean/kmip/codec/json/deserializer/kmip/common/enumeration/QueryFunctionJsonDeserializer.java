package org.purpleBean.kmip.codec.json.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.enumeration.QueryFunction;

public class QueryFunctionJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<QueryFunction, String> {

    public QueryFunctionJsonDeserializer() {
        super(QueryFunction.kmipTag, QueryFunction.encodingType, String.class, value -> new QueryFunction(QueryFunction.fromName(value)));
    }
}