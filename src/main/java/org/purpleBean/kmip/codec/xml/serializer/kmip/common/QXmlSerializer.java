package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.Q;

import java.math.BigInteger;

public class QXmlSerializer extends AbstractKmipDataTypeXmlSerializer<Q, BigInteger> {

    public QXmlSerializer() {
        super(Q::getValue);
    }
}