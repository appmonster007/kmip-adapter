package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.common.QString;

import java.nio.ByteBuffer;

public class QStringTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<QString, ByteBuffer> {

    public QStringTtlvSerializer() {
        super(QString::getValue);
    }
}