package org.purpleBean.kmip;

public interface KeyValue extends KmipDataType {
    KmipTag kmipTag = new KmipTag(KmipTag.Standard.KEY_VALUE);
}
