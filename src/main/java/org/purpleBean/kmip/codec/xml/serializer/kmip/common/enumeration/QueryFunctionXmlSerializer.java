package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.common.enumeration.QueryFunction;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class QueryFunctionXmlSerializer extends AbstractKmipXmlSerializer<QueryFunction, String> {

    public QueryFunctionXmlSerializer() {
        super(QueryFunction::getDescription);
    }
}