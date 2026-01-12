package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.P;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

import java.math.BigInteger;

public class PXmlSerializer extends AbstractKmipXmlSerializer<P, BigInteger> {

    public PXmlSerializer() {
        super(P::getValue);
    }
}