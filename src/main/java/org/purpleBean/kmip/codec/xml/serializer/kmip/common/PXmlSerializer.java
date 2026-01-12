package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.P;

import java.math.BigInteger;

public class PXmlSerializer extends AbstractKmipXmlSerializer<P, BigInteger> {

    public PXmlSerializer() {
        super(P::getValue);
    }
}