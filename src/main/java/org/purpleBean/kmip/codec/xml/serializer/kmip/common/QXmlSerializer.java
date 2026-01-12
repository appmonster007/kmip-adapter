package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.Q;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

import java.math.BigInteger;

public class QXmlSerializer extends AbstractKmipXmlSerializer<Q, BigInteger> {

    public QXmlSerializer() {
        super(Q::getValue);
    }
}