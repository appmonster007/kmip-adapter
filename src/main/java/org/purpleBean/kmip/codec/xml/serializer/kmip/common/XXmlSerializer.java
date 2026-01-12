package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.X;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

import java.math.BigInteger;

public class XXmlSerializer extends AbstractKmipXmlSerializer<X, BigInteger> {

    public XXmlSerializer() {
        super(X::getValue);
    }
}