package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.enumeration.QueryFunction;

public class QueryFunctionXmlSerializer extends AbstractKmipXmlSerializer<QueryFunction, String> {

    public QueryFunctionXmlSerializer() {
        super(QueryFunction::getDescription);
    }
}