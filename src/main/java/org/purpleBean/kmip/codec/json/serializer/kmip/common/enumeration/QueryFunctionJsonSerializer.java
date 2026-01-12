package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.QueryFunction;

public class QueryFunctionJsonSerializer extends AbstractKmipJsonSerializer<QueryFunction, String> {

    public QueryFunctionJsonSerializer() {
        super(QueryFunction::getDescription);
    }
}