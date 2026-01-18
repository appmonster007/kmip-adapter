package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.PrimeExponentP;

import java.math.BigInteger;

public class PrimeExponentPXmlSerializer extends AbstractKmipDataTypeXmlSerializer<PrimeExponentP, BigInteger> {

    public PrimeExponentPXmlSerializer() {
        super(PrimeExponentP::getValue);
    }
}