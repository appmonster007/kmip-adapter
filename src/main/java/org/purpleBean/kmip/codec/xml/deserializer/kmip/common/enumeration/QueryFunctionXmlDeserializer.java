package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.enumeration.QueryFunction;

public class QueryFunctionXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<QueryFunction, String> {

    public QueryFunctionXmlDeserializer() {
        super(QueryFunction.kmipTag, QueryFunction.encodingType, String.class, value -> new QueryFunction(QueryFunction.fromName(value)));
    }
}