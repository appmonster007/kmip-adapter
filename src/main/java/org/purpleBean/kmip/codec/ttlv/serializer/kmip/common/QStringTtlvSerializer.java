package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.QString;

import java.nio.ByteBuffer;

public class QStringTtlvSerializer extends AbstractKmipTtlvSerializer<QString, ByteBuffer> {

    public QStringTtlvSerializer() {
        super(QString::getValue);
    }
}