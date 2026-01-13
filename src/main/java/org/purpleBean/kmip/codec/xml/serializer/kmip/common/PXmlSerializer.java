package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.P;

import java.math.BigInteger;

public class PXmlSerializer extends AbstractKmipDataTypeXmlSerializer<P, BigInteger> {

    public PXmlSerializer() {
        super(P::getValue);
    }
}