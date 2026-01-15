package org.purpleBean.kmip;

public interface KeyValue extends KmipDataType {
    KmipTag kmipTag = KmipTag.Standard.KEY_VALUE.inst();
}
